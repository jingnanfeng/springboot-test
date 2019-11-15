package cn.com.nanfeng.auth2test.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-10-22 22:11
 */
@Data
public class MyUser implements Serializable {
    private static final long serialVersionUID = 9040545297444352071L;

    private String username;
    private String password;

    private boolean accountNonExpired = true;

    private boolean accountNonLocked = true;

    private boolean credentialsNonExpired = true;

    private boolean enable = true;
}
