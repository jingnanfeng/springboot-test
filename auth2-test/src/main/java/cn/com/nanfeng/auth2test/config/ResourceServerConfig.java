package cn.com.nanfeng.auth2test.config;


import cn.com.nanfeng.auth2test.handler.MyAuthenticationSuccessHandler;
import cn.com.nanfeng.auth2test.handler.MyAuthentioncationFailHandler;
import cn.com.nanfeng.auth2test.smscode.SmsAuthenticationConfig;
import cn.com.nanfeng.auth2test.smscode.SmsCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author MrBird
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private MyAuthenticationSuccessHandler authenticationSucessHandler;
    @Autowired
    private MyAuthentioncationFailHandler authenticationFailureHandler;
    @Autowired
    private SmsCodeFilter smsCodeFilter;
    @Autowired
    private SmsAuthenticationConfig smsAuthenticationConfig;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 添加短信验证码校验过滤器
        http.addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)
                // 表单登录
                .formLogin()
                // 处理表单登录 URL
                .loginProcessingUrl("/login")
                // 处理登录成功
                .successHandler(authenticationSucessHandler)
                // 处理登录失败
                .failureHandler(authenticationFailureHandler)
                .and()
                .authorizeRequests() // 授权配置
                .antMatchers("/code/sms").permitAll()
                .anyRequest()  // 所有请求
                .authenticated() // 都需要认证
                .and()
                .csrf().disable()
                .apply(smsAuthenticationConfig);
    }
}
