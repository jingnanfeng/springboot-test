package cn.com.nanfeng.redistest.service.impl;

import cn.com.nanfeng.redistest.model.po.Book;
import cn.com.nanfeng.redistest.service.RedisService;
import cn.com.nanfeng.redistest.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2020-06-12 22:09
 */
@Service
@Slf4j
public class RedisServiceImpl implements RedisService {

    @Resource
    private RedisUtil redisUtil;
    @Resource
    private RedisTemplate<String,Object> redisTemplate;
    @Resource
    private HashOperations<String,String,Object> hashOperations;
    @Resource
    private StringRedisTemplate stringRedisTemplate;


    @Override
    public String getRedisKey(String key) {
        String value = (String) redisTemplate.opsForValue().get(key);
        log.info("value,[{}]",value);
        return value;
    }

    @Override
    public void addRedisValue(String key, String value) {
        redisTemplate.opsForValue().setIfAbsent(key,value,28, TimeUnit.HOURS);
    }

    @Override
    public Book getBookById(String id) {
        if (hashOperations.hasKey("book",id)){
            return (Book) hashOperations.get("book",id);
        }else {
            log.info("使用mysql数据库");
            Book book = new Book();
            book.setBId(1001);
            book.setBName("西游记");
            hashOperations.put("book",id,book);
            return book;
        }
    }
}
