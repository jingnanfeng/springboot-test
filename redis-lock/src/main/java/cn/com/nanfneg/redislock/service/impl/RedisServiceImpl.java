package cn.com.nanfneg.redislock.service.impl;

import cn.com.nanfneg.redislock.service.IRedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @author liutao
 * @Title RedisServiceImpl
 * @Description redisService的实现类
 * @date 2020-06-15 22:36
 */
@Service
@Slf4j
public class RedisServiceImpl implements IRedisService {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;



    @Override
    public String getRedis(String key) {
        //获取key的值
        String value = (String)redisTemplate.opsForValue().get(key);
        log.info("value:[{}]",value);
        return value;
    }

    @Override
    public boolean exists(String key) {
        boolean flag = true;
        //获取key的值
        String value = (String)redisTemplate.opsForValue().get(key);
        //判断key的值是否存在
        if (StringUtils.isEmpty(value)){
            flag = false;
        }
        return flag;
    }
}
