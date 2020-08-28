package cn.com.nanfeng.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2020-07-15 10:38
 */
@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
@EnableFeignClients
public class SecurityGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityGatewayApplication.class,args);
    }
}
