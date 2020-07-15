package cn.com.nanfeng.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2020-07-15 9:21
 */
@SpringBootApplication
@EnableEurekaServer
public class SecurityDiscoveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityDiscoveryApplication.class,args);
    }

}
