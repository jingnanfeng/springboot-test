package cn.com.nanfeng.boot.annotation;

import java.lang.annotation.*;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2020-05-10 12:23
 */
@Documented
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ServiceExcepCatch {
}
