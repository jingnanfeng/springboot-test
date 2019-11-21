package cn.com.nanfeng.junittest.web;

import cn.com.nanfeng.junittest.exception.BusinessException;
import cn.com.nanfeng.junittest.exception.ErrorCodeEnum;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-11-18 16:08
 */
@ControllerAdvice
public class HandlerException {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handleUserNotExistsException(RuntimeException e) {
        Map<String, Object> map = new HashMap<>(1);
        map.put("message", e.getMessage());
        return map;
    }

    /**
     * 统一处理请求参数校验（普通传参）
     * @param e
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleConstrainViolationException(ConstraintViolationException e){
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            throw new BusinessException(ErrorCodeEnum.B10001.getCode(),violation.getMessage());
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void vaildExceptionHandler(MethodArgumentNotValidException e){
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        for (ObjectError error : allErrors) {
            throw new BusinessException(ErrorCodeEnum.B10001.getCode(),error.getDefaultMessage());
        }
    }
}
