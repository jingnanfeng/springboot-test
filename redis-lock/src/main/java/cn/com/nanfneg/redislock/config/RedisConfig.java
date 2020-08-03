package cn.com.nanfneg.redislock.config;

import cn.com.nanfneg.redislock.runner.BloomFilterHelper;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.hash.Funnel;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.lang.reflect.Method;
import java.time.Duration;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-11-05 16:46
 */
@Configuration
@EnableCaching
public class RedisConfig {


    /**
     * 自定义缓存key的生成策略
     */
    @Bean
    public KeyGenerator keyGenerator(){
        return new KeyGenerator(){
            @Override
            public Object generate(Object target, Method method,Object...params){
                StringBuffer sb = new StringBuffer();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }

    /**
     * retemplate相关配置
     * @param factory
     * @return
     */
    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String,Object> template = new RedisTemplate<>();
        //配置连接工厂
        template.setConnectionFactory(factory);
        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值(默认使用JDK的序列化方式)
        Jackson2JsonRedisSerializer jsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        //指定要序列化的域，fileld,get和set,以及修饰符范围,ANY是有包括private和public
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        //指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等都会抛出异常
        jsonRedisSerializer.setObjectMapper(objectMapper);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        //值采用json序列化
        template.setValueSerializer(stringRedisSerializer);
        //使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(stringRedisSerializer);
        //设置hash key 和value序列化模式
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(jsonRedisSerializer);
        template.afterPropertiesSet();

        return template;
    }

    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate){
        //全部redis缓存过期时间
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration
                .defaultCacheConfig().entryTtl(Duration.ofDays(1));
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisTemplate.getConnectionFactory());
        return new RedisCacheManager(redisCacheWriter,redisCacheConfiguration);
    }

    /**
     * 对hash类型的数据操作
     * @param redisTemplate
     * @return
     */
    @Bean
    public HashOperations<String,String,Object> hashOperations(RedisTemplate<String,Object> redisTemplate){
        return redisTemplate.opsForHash();
    }

    /**
     * 对String类型的操作数据
     * @param redisTemplate
     * @return
     */
    @Bean
    public ValueOperations<String,Object> valueOperations(RedisTemplate<String,Object> redisTemplate){
        return redisTemplate.opsForValue();
    }

    /**
     * 无序集合
     * @param redisTemplate
     * @return
     */
    @Bean
    public SetOperations<String,Object> setOperations(RedisTemplate<String,Object> redisTemplate){
        return redisTemplate.opsForSet();
    }

    /**
     *有序集合
     * @param redisTemplate
     * @return
     */
    @Bean
    public ZSetOperations<String,Object> zSetOperations(RedisTemplate<String,Object> redisTemplate){
        return redisTemplate.opsForZSet();
    }

    /**
     * 注册bloomFilterHelper
     * @return
     */
    @Bean
    public BloomFilterHelper<String> initBloomFilterHelper(){
        return new BloomFilterHelper<>((Funnel<String>) (from,into) -> into.putString(from, Charsets.UTF_8)
        .putString(from,Charsets.UTF_8),1000000,0.01);
    }

}
