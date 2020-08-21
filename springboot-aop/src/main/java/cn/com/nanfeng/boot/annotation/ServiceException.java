package cn.com.nanfeng.boot.annotation;

import java.lang.annotation.*;

/**
 * @author liutao
 * @title ServiceException
 * @description service统一异常处理
 * @date 2020-08-03 15:04
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ServiceException {
}
