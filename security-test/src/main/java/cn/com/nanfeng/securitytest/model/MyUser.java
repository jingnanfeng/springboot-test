package cn.com.nanfeng.securitytest.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-10-19 16:12
 */
@Data
public class MyUser implements Serializable {
    private static final long serialVersionUID = 4092563010295332724L;

    private String username;

    private String password;

    private boolean accountNonEcpired = true;

    private boolean accountNonLoaked = true;

    private boolean credentialsNonExpired = true;

    private boolean enable = true;

}
