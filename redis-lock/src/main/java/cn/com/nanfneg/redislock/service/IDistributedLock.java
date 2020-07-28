package cn.com.nanfneg.redislock.service;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2020-05-30 22:28
 */
public interface IDistributedLock {

    /**
     * 分布式锁
     * @param key 主键
     * @return
     */
    boolean lock(String key);

    /**
     * 分布式锁
     * @param key
     * @param retryCount
     * @return
     */
    boolean lock(String key,int retryCount);

    /**
     * 分布式锁
     * @param key 主键
     * @param retryCount 重试次数
     * @param sleepMillis 时间间隔
     * @return
     */
    boolean lock(String key,int retryCount,long sleepMillis);

    /**
     * 设置睡眠时间
     * @param key 主键
     * @param expire 有效时间
     * @return
     */
    boolean lock(String key,long expire);

    /**
     * 分布式锁
     * @param key 主键
     * @param expire 有效时间
     * @param retryCount 重试次数
     * @return
     */
    boolean lock(String key,long expire,int retryCount);

    /**
     *分布式锁
     * @param key 主键
     * @param expire 有效时间
     * @param retryCount 重试次数
     * @param sleepMillis 时间间隔
     * @return
     */
    boolean lock(String key,long expire,int retryCount,long sleepMillis);

    /**
     * 释放锁
     * @param key
     * @return
     */
    boolean unlock(String key);


}
