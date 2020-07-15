package cn.com.nanfeng.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2020-07-13 23:33
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
@EnableFeignClients(basePackages = {"cn.com.nanfeng.cloud"})
public class SecurityUaaApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecurityUaaApplication.class,args);
    }
}
