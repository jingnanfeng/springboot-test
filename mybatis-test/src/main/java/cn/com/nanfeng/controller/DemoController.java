package cn.com.nanfeng.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2020-07-24 15:38
 */
@RestController
public class DemoController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/vod/testNginx")
    public String testNginx(){
        return port+"欢迎使用ngnix反向代理";
    }

}
