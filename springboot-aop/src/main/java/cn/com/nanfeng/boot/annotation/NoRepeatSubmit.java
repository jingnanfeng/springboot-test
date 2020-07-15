package cn.com.nanfeng.boot.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2020-07-07 22:59
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NoRepeatSubmit {

    /**
     * 重复统计时长
     * @return
     */
    int time() default 1;

}
