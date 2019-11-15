package cn.com.nanfeng.securitytest.imagecode;

import org.springframework.security.core.AuthenticationException;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-10-20 10:45
 */
public class ValidateCodeException extends AuthenticationException {


    private static final long serialVersionUID = -189130178256312783L;

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
