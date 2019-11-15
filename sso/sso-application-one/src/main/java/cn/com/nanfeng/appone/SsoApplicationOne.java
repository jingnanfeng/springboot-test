package cn.com.nanfeng.appone;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-10-25 10:48
 */
@EnableOAuth2Sso
@SpringBootApplication
public class SsoApplicationOne {

    public static void main(String[] agrs){
        new SpringApplicationBuilder(SsoApplicationOne.class).run(agrs);
    }

}
