package cn.com.nanfneg.redislock.annotation;

import java.lang.annotation.*;

/**
 * @author liutao
 * @title RepeatSubmitCheck
 * @description 重复提交的校验
 * @date 2020-07-29 10:32
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RepeatSubmitCheck {

    int keepSeconds() default 1;

}
