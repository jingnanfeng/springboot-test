package cn.com.nanfeng.cloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2020-07-14 11:37
 */
@RestController
public class OrderController {

    @GetMapping(value = "/r1")
    public String r1(){
        //UserDto user = (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "访问资源1";
    }
}
