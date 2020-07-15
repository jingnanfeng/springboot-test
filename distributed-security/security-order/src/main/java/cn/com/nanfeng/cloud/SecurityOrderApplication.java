package cn.com.nanfeng.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
/**
 * @author liutao
 * @Title
 * @Description
 * @date 2020-07-13 23:52
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SecurityOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityOrderApplication.class,args);
    }
}
