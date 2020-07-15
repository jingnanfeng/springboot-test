package cn.com.nanfeng.cloud.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2020-07-08 17:44
 */
@RestController
public class LoginController {

   @RequestMapping(value = "/login-success",produces = "text/plain;charset=utf-8")
    public String loginSuccess(){
       return  getUsername() + "登录成功";
   }

   @RequestMapping(value = "/r/r1",produces = "text/plain;charset=utf-8")
    public String testR1(){
       return "访问/r/r1资源成功";
   }

   @RequestMapping(value = "/r/r2",produces = "text/plain;charset=utf-8")
    public String testR2(){
       return "访问r/r2资源成功";
   }

    /**
     * 获取当前的用户信息
     * @return
     */
   private String getUsername(){
       String username = "";
       //当前认证通过的用户身份
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       //获取用户身份
       Object principal = authentication.getPrincipal();
       if (principal == null){
           username = "匿名";
       }
       if (principal instanceof UserDetails){
           UserDetails userDetails = (UserDetails) principal;
           username = userDetails.getUsername();
       }else {
           username = principal.toString();
       }
       return username;
   }
}
