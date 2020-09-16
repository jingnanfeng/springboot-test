package cn.com.nanfeng.boot.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author liutao
 * @date 2020-09-14 17:14
 */
@Aspect
@Component
public class MyLogAspect {

    /**
     * Pointcut表示这是一个切点，@annotation表示这个切点切到一个注解上，后面带该注解的全类名
     * 切面最主要的就是切点，所有的故事都围绕着切点发生
     * logPointCut()代表切点名称
     */
    @Pointcut("@annotation(cn.com.nanfeng.boot.annotation.MyLog)")
    public void logPointCut(){
    }

    /**
     * 环绕通知
     * TODO 如果返回值类型是void 接口无法返回参数
     * @param joinPoint
     */
    @Around("logPointCut()")
    public Object logArount(ProceedingJoinPoint joinPoint){
        //获取方法名称
        String methodName = joinPoint.getSignature().getName();
        //获取入参
        Object[] params = joinPoint.getArgs();

        StringBuilder sb = new StringBuilder();
        for (Object param : params) {
            sb.append(param + ";");
        }
        System.out.println("进入[" + methodName + "]方法，参数为:"+sb.toString());
        //继续执行方法
        Object process = null;
        try {
            process = joinPoint.proceed();
        }catch (Throwable t){
            t.printStackTrace();
        }
        System.out.println(methodName + "方法执行结束");
        return process;
    }

}
