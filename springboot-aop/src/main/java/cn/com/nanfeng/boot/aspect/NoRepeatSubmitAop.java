package cn.com.nanfeng.boot.aspect;

import cn.com.nanfeng.boot.annotation.NoRepeatSubmit;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author liutao
 * @Title 接口防重复切面
 * @Description
 * @date 2020-07-07 23:01
 */
@Component
@Slf4j
@Aspect
public class NoRepeatSubmitAop {

    private static final String JWT_TOKEN_KEY = "jwt-token";

    @Pointcut("@annotation(noRepeatSubmit)")
    public void serviceNoRepeat(NoRepeatSubmit noRepeatSubmit){
    }

    @Around("serviceNoRepeat(noRepeatSubmit)")
    public Object arount(ProceedingJoinPoint point, NoRepeatSubmit noRepeatSubmit) throws Throwable{
        int time = noRepeatSubmit.time();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        return null;
    }

}
