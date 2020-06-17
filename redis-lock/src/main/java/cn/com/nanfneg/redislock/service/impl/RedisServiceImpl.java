package cn.com.nanfneg.redislock.service.impl;

import cn.com.nanfneg.redislock.service.IRedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2020-06-15 22:36
 */
@Service
@Slf4j
public class RedisServiceImpl implements IRedisService {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;



    @Override
    public String getRedis(String key) {
        String value = (String)redisTemplate.opsForValue().get(key);
        log.info("value:[{}]",value);
        return value;
    }
}
