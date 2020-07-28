package cn.com.nanfneg.redislock.service.impl;

import cn.com.nanfneg.redislock.service.IDistributedLock;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
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
@Slf4j
public class RedisDistributedLock implements IDistributedLock {

    /**
     * 解锁脚本，原子操作
     */
    private static final String UNLOCK_SCRIPT =
            "if redis.call(\"get\",KEYS[1]) == ARGV[1]\n"
            + "then\n"
            + "     return redis.call(\"del\",KEYS[1])\n"
            + "else\n"
            + "     return 0\n"
            + "end";

    /**
     * 设置有效时间
     */
   private static final  long TIMEOUT_MILLIS = 30000;
    /**
     * 重试次数,若不设置默认是最大的int
     */
    private static final int  RETRY_COUNT=  Integer.MAX_VALUE;
    /**
     * 时间间隔,默认0.1s
     */
    private static final long SLEEP_MILLIS = 100;

   @Resource
   private RedisTemplate<String,Object> redisTemplate;


   private ThreadLocal<String> lockFlag = new ThreadLocal<>();

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
        //如果是失败并且重试次数大于0
        while ((!result) && retryCount-- > 0){
            try {
                log.info("lock failed,retry...[{}]", retryCount);
                Thread.sleep(sleepMillis);
            }catch (InterruptedException e){
                return false;
            }
            result = setRedis(key, expire);
        }
        return result;
    }

    /**
     * 释放锁
     * @param key
     * @return
     */
    @Override
    public boolean unlock(String key) {

        String value = lockFlag.get();
        RedisScript<Boolean> redisScript = new DefaultRedisScript<>(UNLOCK_SCRIPT,Boolean.class);
        Boolean b = redisTemplate.execute(redisScript, Collections.singletonList(key),value);
        return b;

    }

    /**
     * 插入Redis
     * @param key 主键
     * @param expire 有效时间
     * @return
     */
    private boolean setRedis(String key,long expire){
        String uuid = UUID.randomUUID().toString();
        lockFlag.set(uuid);
        Boolean b = redisTemplate.opsForValue().setIfAbsent(key, uuid, expire, TimeUnit.MILLISECONDS);
        return b;
    }


}
