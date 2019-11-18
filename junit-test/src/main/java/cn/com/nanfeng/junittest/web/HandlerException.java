package cn.com.nanfeng.junittest.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

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
}
