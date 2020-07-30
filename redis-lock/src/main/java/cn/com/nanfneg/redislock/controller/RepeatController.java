package cn.com.nanfneg.redislock.controller;

import cn.com.nanfneg.redislock.annotation.RepeatSubmitCheck;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liutao
 * @title RepeatController
 * @description 测试接口重复提交
 * @date 2020-07-29 11:31
 */
@RestController
@Slf4j
public class RepeatController {

    @RepeatSubmitCheck(keepSeconds = 100)
    @GetMapping("/test/repeatSubmit")
    public String testRepeatSubmit(){
        log.info("测试成功");
        return "SUCCESS";
    }

}
