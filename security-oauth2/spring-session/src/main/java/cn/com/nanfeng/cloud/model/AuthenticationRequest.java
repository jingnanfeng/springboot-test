package cn.com.nanfeng.cloud.model;

import lombok.Data;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2020-07-08 17:12
 */
@Data
public class AuthenticationRequest {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

}
