package cn.com.nanfneg.redislock.service;

/**
 * @author liutao
 * @Title  IRedisService
 * @Description redis是方法定义
 * @date 2020-06-15 22:36
 */
public interface IRedisService {

    /**
     * 得到redis
     * @param key key
     * @return value
     */
    String getRedis(String key);

    /**
     * 判断redis是否存在
     * @param key key
     * @return boolean
     */
    boolean exists(String key);

}
