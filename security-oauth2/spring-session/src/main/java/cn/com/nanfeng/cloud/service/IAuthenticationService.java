package cn.com.nanfeng.cloud.service;

import cn.com.nanfeng.cloud.model.AuthenticationRequest;
import cn.com.nanfeng.cloud.model.UserDto;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2020-07-08 16:12
 */
public interface IAuthenticationService {

    /**
     * 用户认证
     * @param authenticationRequest 用户请求认证，账号密码
     * @return 认证成功的用户信息
     */
    UserDto authentication(AuthenticationRequest authenticationRequest);
}
