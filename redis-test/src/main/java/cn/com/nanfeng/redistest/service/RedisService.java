package cn.com.nanfeng.redistest.service;


import cn.com.nanfeng.redistest.model.po.Book;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2020-06-12 22:09
 */
public interface RedisService {

    /**
     * 得到
     */
    String getRedisKey(String key);

    void addRedisValue(String key,String value);

    Book getBookById(String id);

}
