package cn.com.nanfneg.redislock.runner;

import com.google.common.base.Preconditions;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author liutao
 * @title RedisBloomService
 * @description redis bloom 的操作类
 * @date 2020-08-01 23:07
 */
@Service
public class RedisBloomService {

    @Resource
    private RedisTemplate<String,Integer> redisTemplate;

    /**
     * 根据给定的布隆过滤器添加值
     * @param bloomFilterHelper
     * @param key
     * @param value
     * @param <T>
     */
    public <T> void  addByBloomFilter(BloomFilterHelper<T> bloomFilterHelper,String key,T value){
        Preconditions.checkArgument(bloomFilterHelper != null, "bloomFilterHelper不能为空");
        int[] offset = bloomFilterHelper.murmurHashOffset(value);
        for (int i : offset) {
            redisTemplate.opsForValue().setBit(key,i,true);
        }
    }

    /**
     * 根据给的布隆过滤器判断值是否存在
     * @param bloomFilterHelper
     * @param key
     * @param value
     * @param <T>
     * @return
     */
    public <T> boolean includeByBloomFilter(BloomFilterHelper<T> bloomFilterHelper,String key,T value){
        Preconditions.checkArgument(bloomFilterHelper != null,"bloomFilterHelper不能为空");
        int[] offset = bloomFilterHelper.murmurHashOffset(value);
        for (int i : offset) {
            if (!redisTemplate.opsForValue().getBit(key,i)){
                return false;
            }
        }
        return true;
    }
}
