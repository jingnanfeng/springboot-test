package cn.com.nanfeng.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author liutao
 * @date 2020-08-26 14:19
 */
@Controller
public class TestController {

    @DeleteMapping("/test")
    public String test(@RequestParam("aa")String aa){
        return "aa";
    }

    @PostMapping("/hello/{aa}/{bb}")
    @ResponseBody
    public String hello(@PathVariable String aa,@PathVariable String bb){
        return "bb";
    }

}
