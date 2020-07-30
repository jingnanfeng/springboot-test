package cn.com.nanfneg.redislock.controller;


import cn.com.nanfeng.commit.exception.RepeatSubmitException;
import cn.com.nanfeng.commit.response.WrapMapper;
import cn.com.nanfeng.commit.response.Wrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author liutao
 * @date 2020/7/29
 */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 参数非法异常.
     *
     * @param e the e
     *
     * @return the wrapper
     */
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Wrapper repeatSubmitException(RepeatSubmitException e) {
        log.error("参数非法异常={}", e.getMessage(), e);
        return WrapMapper.wrap(e.getCode(),e.getMessage());
    }

}
