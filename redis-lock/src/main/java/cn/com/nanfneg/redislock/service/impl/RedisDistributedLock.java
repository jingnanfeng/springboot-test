package cn.com.nanfneg.redislock.service.impl;

import cn.com.nanfneg.redislock.service.IDistributedLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2020-05-30 22:29
 */
@Service

public class RedisDistributedLock implements IDistributedLock {

    private static final Logger logger = LoggerFactory.getLogger(RedisDistributedLock.class);

    /**
     * 设置有效时间
     */
   public static final  long TIMEOUT_MILLIS = 30000;
    /**
     * 重试次数
     */
   public static final int  RETRY_COUNT=  Integer.MAX_VALUE;

    /**
     * 时间间隔
     */
   public static final long SLEEP_MILLIS = 500;

   @Autowired
   private RedisTemplate<String,Object> redisTemplate;

    @Override
    public boolean lock(String key) {
        return lock(key,TIMEOUT_MILLIS,RETRY_COUNT,SLEEP_MILLIS);
    }

    @Override
    public boolean lock(String key, int retryCount) {
        return lock(key,TIMEOUT_MILLIS,retryCount,SLEEP_MILLIS);
    }

    @Override
    public boolean lock(String key, int retryCount, long sleepMillis) {
        return lock(key,TIMEOUT_MILLIS,retryCount,sleepMillis);
    }

    @Override
    public boolean lock(String key, long expire) {
        return lock(key,expire,RETRY_COUNT,SLEEP_MILLIS);
    }

    @Override
    public boolean lock(String key, long expire, int retryCount) {
        return lock(key, expire, retryCount,SLEEP_MILLIS);
    }


    @Override
    public boolean lock(String key, long expire, int retryCount, long sleepMillis) {
        boolean result = setRedis(key, expire);
        //设置成功，创建一个定时任务去刷新锁的时间
        if (result){
            new ScheduledThreadPoolExecutor(1).scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    redisTemplate.expire(key,expire,TimeUnit.MILLISECONDS);
                }
            },0,expire/3,TimeUnit.MILLISECONDS);
        }
        //如果是失败并且重试次数大于0
        while ((!result) && retryCount-- > 0){
            try {
                logger.debug("lock failed,retry..." + retryCount);
                Thread.sleep(sleepMillis);
            }catch (InterruptedException e){
                return false;
            }
            result = setRedis(key, expire);
        }
        return result;
    }

    @Override
    public boolean releaseLock(String key) {
        Boolean delete = redisTemplate.delete(key);
        return delete;
    }

    /**
     * 插入Redis
     * @param key 主键
     * @param expire 有效时间
     * @return
     */
    private boolean setRedis(String key,long expire){
        String uuid = UUID.randomUUID().toString();
        Boolean b = redisTemplate.opsForValue().setIfAbsent(key, uuid, expire, TimeUnit.MILLISECONDS);
        return b;
    }


}
