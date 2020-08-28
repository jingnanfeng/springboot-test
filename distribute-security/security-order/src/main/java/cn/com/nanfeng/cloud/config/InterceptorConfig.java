package cn.com.nanfeng.cloud.config;

import cn.com.nanfeng.cloud.interceptor.GlobalInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2020-07-19 11:23
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Resource
    private GlobalInterceptor globalInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(globalInterceptor).addPathPatterns("/**");
    }
}
