package cn.com.nanfeng.apptwo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-10-25 11:28
 */
@EnableOAuth2Sso
@SpringBootApplication
public class SsoApplicationTwo {

    public static void main(String[] args) {
        new SpringApplicationBuilder(SsoApplicationTwo.class).run(args);
    }
}
