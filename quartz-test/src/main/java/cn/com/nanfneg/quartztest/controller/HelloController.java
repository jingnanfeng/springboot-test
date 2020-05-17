package cn.com.nanfneg.quartztest.controller;

import cn.com.nanfneg.quartztest.service.IMyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2020-05-17 11:21
 */
@RestController
public class HelloController {

    @Resource
    private IMyService myService;

    @GetMapping("/hello")
    public String hello(){
        String str = myService.helloWorld();
        return str;
    }
}
