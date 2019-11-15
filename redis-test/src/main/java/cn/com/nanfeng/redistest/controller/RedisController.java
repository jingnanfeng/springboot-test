package cn.com.nanfeng.redistest.controller;

import cn.com.nanfeng.redistest.model.UserEntity;
import cn.com.nanfeng.redistest.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.swing.*;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-11-07 9:29
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    private static final Logger logger = LoggerFactory.getLogger(RedisController.class);

    /**
     *  redis中存储的过期时间60s
     */
    private static int ExpireTime = 60;

    @Resource
    private RedisUtil redisUtil;

    @GetMapping("/set")
    public boolean redisSet(String key,String value){
        return redisUtil.set(key,value);
    }

    @GetMapping("/get")
    public Object redisGet(String key){
        return redisUtil.get(key);
    }

    @GetMapping("/expire")
    public boolean expire(String key){
        return redisUtil.expire(key,ExpireTime);
    }

}
