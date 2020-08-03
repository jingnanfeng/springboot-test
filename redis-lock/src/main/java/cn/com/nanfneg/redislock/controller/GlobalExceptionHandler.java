package cn.com.nanfneg.redislock.controller;


import cn.com.nanfeng.commit.exception.BusinessException;
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
     * 重复提交异常
     *
     * @param e the e
     *
     * @return the wrapper
     */
    @ExceptionHandler(RepeatSubmitException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Wrapper repeatSubmitException(RepeatSubmitException e) {
        log.error("重复提交异常={}", e.getMessage(), e);
        return WrapMapper.wrap(e.getCode(),e.getMessage());
    }

    /**
     * 业务处理异常
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Wrapper BusinessException(BusinessException e){
        log.error("业务处理异常",e.getMessage(),e);
        return WrapMapper.wrap(e.getCode(),e.getMessage());
    }

}
