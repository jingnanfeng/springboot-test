package cn.com.nanfneg.redislock.aspect;

import cn.com.nanfeng.commit.exception.BusinessException;
import cn.com.nanfeng.commit.exception.ErrorCodeEnum;
import cn.com.nanfeng.commit.exception.RepeatSubmitException;
import cn.com.nanfeng.commit.response.WrapMapper;
import cn.com.nanfneg.redislock.annotation.RepeatSubmitCheck;
import cn.com.nanfneg.redislock.service.IRedisService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sun.security.provider.MD5;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @author liutao
 * @title RepeatSubmitAspect
 * @description 重复提交的切面
 * @date 2020-07-29 10:36
 */
@Aspect
@Component
@Slf4j
public class RepeatSubmitAspect {

    private static final String USER_KEY = "token";

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Pointcut("@annotation(cn.com.nanfneg.redislock.annotation.RepeatSubmitCheck)")
    public void pointcut(){}

    @Before("pointcut()")
    public Object arountCheck(JoinPoint point) throws RepeatSubmitException{
        //获取注释中方法
        Method method = ((MethodSignature) point.getSignature()).getMethod();
        RepeatSubmitCheck repeatSubmitCheck = method.getAnnotation(RepeatSubmitCheck.class);
        int seconds = repeatSubmitCheck.keepSeconds();
        //获取当前请求
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //校验是否重复提交
        boolean exists = executeValidation(request, seconds);
        if (!exists){
            throw new RepeatSubmitException(ErrorCodeEnum.COMMON1110.getCode(),ErrorCodeEnum.COMMON1110.getMessage());
        }
        return null;
    }

    /**
     * 校验是否重复提交
     * @param request  请求报文
     * @param expire 超时，单位s
     * @throws IOException
     */
    private boolean executeValidation(HttpServletRequest request,int expire){
        //获取请求的uri
        String requestURI = request.getRequestURI();
        //从请求头中获取用户信息
        String userKey = String.valueOf(request.getAttribute(USER_KEY));
        //这里可以进行加密,确保url和用户信息不被泄露
        //判断uri+userKey是否存在
        boolean exists = stringRedisTemplate.opsForValue().setIfAbsent(requestURI+userKey,USER_KEY,expire, TimeUnit.SECONDS);
        return exists;
    }

}
