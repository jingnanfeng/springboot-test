package cn.com.nanfneg.redislock.config;

import cn.com.nanfneg.redislock.service.IDistributedLock;
import cn.com.nanfneg.redislock.service.impl.RedisDistributedLock;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2020-07-28 21:40
 */
@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class DistributeLockAutoConfiguration {

    @Bean
    @ConditionalOnBean(RedisTemplate.class)
    public IDistributedLock redisDistributeLock(RedisTemplate redisTemplate){
        return new RedisDistributedLock();
    }


}
