package cn.com.nanfneg.redislock.aspect;

import cn.com.nanfneg.redislock.annotation.RedisLock;
import cn.com.nanfneg.redislock.config.DistributeLockAutoConfiguration;
import cn.com.nanfneg.redislock.service.IDistributedLock;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MemberSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2020-07-28 21:50
 */
@Slf4j
@Aspect
@Configuration
@ConditionalOnBean(IDistributedLock.class)
@AutoConfigureAfter(DistributeLockAutoConfiguration.class)
public class DistributedLockAspectConfiguration {

    @Resource
    private IDistributedLock distributedLock;

    @Pointcut("@annotation(cn.com.nanfneg.redislock.annotation.RedisLock)")
    private void lockPoint(){}

    @Around("lockPoint()")
    public Object arount(ProceedingJoinPoint point) throws Throwable{
        Method method = ((MethodSignature) point.getSignature()).getMethod();
        RedisLock redisLock = method.getAnnotation(RedisLock.class);
        String key = redisLock.key();
        if (StringUtils.isEmpty(key)){
            Object[] args = point.getArgs();
            key = Arrays.toString(args);
        }
        int retryTimes = redisLock.action().equals(RedisLock.LockFailAction.CONTINUE) ? redisLock.retryTimes() : 0;
        //获取分布式锁
        boolean lock = distributedLock.lock(key,redisLock.keepMills(),retryTimes,redisLock.sleepMills());
        if (!lock){
            log.info("get lock failed : [{}]" , key);
            return null;
        }
        //执行方法之后，释放分布式锁
        log.info("get lock success:[{}]",key);
        try {
            return point.proceed();
        }catch (Exception e){
            log.error("execute locked method occured an exception",e);
        }finally {
            //释放锁
            boolean result = distributedLock.unlock(key);
            log.debug("release lock :[{}],[{}]",key,(result ? "succeess" : "failed"));
        }
        return null;
    }

}
