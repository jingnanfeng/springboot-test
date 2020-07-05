package cn.com.nanfeng.boot.controller;

import cn.com.nanfeng.boot.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2020-07-05 22:33
 */
@RestController
public class TestController {

    @Resource
    private TestService testService;


    @GetMapping("/testAop")
    public String testAop(){
        testService.testLog();
        return "success";
    }

}
