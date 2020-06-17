package cn.com.nanfneg.redislock;

import cn.com.nanfneg.redislock.service.IRedisService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RedisLockApplicationTests {

    @Resource
    private IRedisService redisService;


    @Test
    public void testGetRedis(){
       String value = redisService.getRedis("k1");
        log.info("value:[{}]",value);
   }

}
