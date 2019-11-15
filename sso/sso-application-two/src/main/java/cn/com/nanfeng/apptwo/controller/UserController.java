package cn.com.nanfeng.apptwo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-10-25 11:29
 */
@RestController
public class UserController {

    @GetMapping("user")
    public Authentication user(Authentication authentication) {
        return authentication;
    }

}
