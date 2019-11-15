package cn.com.nanfeng.sso.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-10-24 18:30
 */
@Data
public class MyUser implements Serializable {
    private static final long serialVersionUID = 594340289501229451L;

    private String username;

    private String password;

    private boolean accountNonExpired = true;

    private boolean accountNonLocked = true;

    private boolean credentialsNonExpired = true;

    private boolean enable = true;

}
