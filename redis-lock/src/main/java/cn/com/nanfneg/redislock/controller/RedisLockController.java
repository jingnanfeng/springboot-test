package cn.com.nanfneg.redislock.controller;

import cn.com.nanfneg.redislock.annotation.RedisLock;
import cn.com.nanfneg.redislock.service.IDistributedLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2020-05-30 20:48
 */
@RestController
@Slf4j
public class RedisLockController {

    @Resource
    private IDistributedLock distributedLock;

    @GetMapping("/testLock")
    public String testLock(){
        String key = "lock111111111111";
        boolean lock = distributedLock.lock(key);
        try {
            if (lock){
                log.info("获取锁成功");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            distributedLock.unlock(key);
        }
        return "success";
    }

    @GetMapping("/testAopLock")
    @RedisLock(key = "lock:222222222")
    public String testAopLock(String key){
        return "success";
    }
}
