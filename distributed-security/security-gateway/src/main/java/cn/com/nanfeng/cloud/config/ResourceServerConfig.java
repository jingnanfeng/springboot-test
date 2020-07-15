package cn.com.nanfeng.cloud.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.annotation.Resource;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2020-07-14 11:39
 */
@Configuration
public class ResourceServerConfig {

    public static final String RESOURCE_ID = "res1";

    @Resource
    private TokenStore tokenStore;


    /**
     * 配置UAA资源
     */
    @Configuration
    @EnableResourceServer
    public class UAAServiceConfig extends ResourceServerConfigurerAdapter{

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
            resources
                    .tokenStore(tokenStore)
                    .resourceId(RESOURCE_ID)
                    .stateless(true);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/uaa/**").permitAll();
        }
    }

    @Configuration
    @EnableResourceServer
    public class OrderServiceConfig extends ResourceServerConfigurerAdapter{
        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
            resources
                    .tokenStore(tokenStore)
                    .resourceId(RESOURCE_ID)
                    .stateless(true);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/order/**").access("#oauth2.hasScope('ROLE_API')");
        }
    }
}
