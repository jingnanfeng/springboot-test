package cn.com.nanfeng.sso;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-10-24 18:18
 */
@SpringBootApplication
public class SsoServerApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(SsoServerApplication.class).run(args);
    }
}
