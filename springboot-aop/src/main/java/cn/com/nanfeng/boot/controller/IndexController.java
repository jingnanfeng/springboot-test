package cn.com.nanfeng.boot.controller;

import cn.com.nanfeng.boot.annotation.LoginRequired;
import cn.com.nanfeng.boot.annotation.MyLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liutao
 * @date 2020-09-14 16:59
 */
@RestController
public class IndexController {

    @GetMapping("/sourceA")
    public String sourceA(){
        return "你正在访问sourceA资源";
    }

    @LoginRequired
    @GetMapping("/sourceB")
    public String sourceB(){
        return "你正在访问sourceB资源";
    }

    @MyLog
    @GetMapping("/sourceC/{name}")
    public String source(@PathVariable("name")String name){
        return "您正在访问sourceC资源";
    }



}
