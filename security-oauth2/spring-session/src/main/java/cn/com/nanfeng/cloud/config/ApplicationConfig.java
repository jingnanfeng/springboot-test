package cn.com.nanfeng.cloud.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * @author liutao
 * @Title applicationContext.xml
 * @Description
 * @date 2020-07-08 13:45
 */
@Configuration
@ComponentScan(basePackages = "cn.com.nanfeng.cloud",
excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,value = Controller.class)})
public class ApplicationConfig {
}
