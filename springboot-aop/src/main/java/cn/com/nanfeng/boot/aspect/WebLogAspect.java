package cn.com.nanfeng.boot.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2020-07-05 22:10
 */
@Component
@Aspect
@Slf4j
public class WebLogAspect {

    /**
     * 切入点描述 这个service包的切入点
     * 签名，可以理解成切入点的一个名称
     */
    @Pointcut("execution(public * cn.com.nanfeng.boot.service..*.*(..))")
    public void serviceLog(){}

    /**
     * 在切人点的方法run之前要干的
     * @param joinPoint
     */
    @Before("serviceLog()")
    public void logBeforeService(JoinPoint joinPoint){
        log.info(joinPoint.getSignature().getDeclaringTypeName() +" -> "+ joinPoint.getSignature().getName());
    }


}
