package cn.com.nanfeng.securitytest.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-10-19 13:50
 */
@RestController
public class TestController {

    @GetMapping("/hello")
    public String hello(){
        return "hello spring security";
    }

    @GetMapping("/index")
    public Object index(Authentication authentication){
        return authentication;
    }

}
