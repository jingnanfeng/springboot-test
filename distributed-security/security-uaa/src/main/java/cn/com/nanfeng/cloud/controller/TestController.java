package cn.com.nanfeng.cloud.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2020-07-16 15:15
 */
@RestController
public class TestController {


    @GetMapping("/test")
    public String test(){
        Object ss = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "测试";
    }

}
