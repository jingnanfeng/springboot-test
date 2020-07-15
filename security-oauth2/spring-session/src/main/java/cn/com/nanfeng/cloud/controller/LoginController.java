package cn.com.nanfeng.cloud.controller;

import cn.com.nanfeng.cloud.model.AuthenticationRequest;
import cn.com.nanfeng.cloud.model.UserDto;
import cn.com.nanfeng.cloud.service.IAuthenticationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2020-07-08 17:44
 */
@RestController
public class LoginController {

    @Resource
    private IAuthenticationService authenticationService;

    @PostMapping(value = "/login",produces = "text/plain;charset=utf-8")
    public String login(AuthenticationRequest authenticationRequest, HttpSession session){
        //查询用户
        UserDto userDto = authenticationService.authentication(authenticationRequest);
        //存入session
        session.setAttribute(UserDto.SESSION_USER_KEY,userDto);
        return userDto.getUsername() + "登录成功";
    }

    @GetMapping(value = "/user/getUser",produces = "text/plain;charset=utf-8")
    public String getUser(HttpSession session){
        String username = null;
        Object object = session.getAttribute(UserDto.SESSION_USER_KEY);
        if (object == null){
            username = "获取用户名失败";
        }else {
            UserDto userDto = (UserDto) object;
            username = userDto.getUsername() + "获取用户名成功";
        }
        return username;
    }
    @GetMapping(value = "/user/getUserDetail",produces = "text/plain;charset=utf-8")
    public String getUserDetail(HttpSession session){
        String username = null;
        Object object = session.getAttribute(UserDto.SESSION_USER_KEY);
        if (object == null){
            username = "获取用户名失败";
        }else {
            UserDto userDto = (UserDto) object;
            username = userDto.getUsername() + "获取用户名成功";
        }
        return username;
    }


    @GetMapping(value = "/logout",produces = "text/plain;charset=utf-8")
    public String logout(HttpSession session){
        session.invalidate();
        return "退出登录成功";
    }


}
