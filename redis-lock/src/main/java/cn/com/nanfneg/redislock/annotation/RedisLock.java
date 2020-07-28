package cn.com.nanfneg.redislock.annotation;

import java.lang.annotation.*;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2020-07-28 17:28
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface RedisLock {

    /**
     * 锁的资源，redis的key
     */
    String key() default "default";

    /**
     * 持锁时间，默认时30000毫秒
     */
    long keepMills() default 30000;

    /**
     * 执行失败时的操作
     * @return
     */
    LockFailAction action() default LockFailAction.CONTINUE;

    /**
     * 当获取失败时候的动作
     */

    enum LockFailAction{
        /**
         * 放弃
         */
        GIVEUP,
        /**
         * 继续
         */
        CONTINUE,
        ;
    }

    /**
     * 重试的时间间隔，设置GIVEUP忽略此项
     * @return
     */
    long sleepMills() default 100;

    /**
     * 重试次数
     */
    int retryTimes() default 10;

}
