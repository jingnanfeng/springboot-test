package cn.com.nanfeng.securitytest.config;

import cn.com.nanfeng.securitytest.handler.MyAuthenticationFailureHandler;
import cn.com.nanfeng.securitytest.handler.MyAuthenticationSuccessHandler;
import cn.com.nanfeng.securitytest.imagecode.ValidateCodeFilter;
import cn.com.nanfeng.securitytest.session.MySessionExpiredStrategy;
import cn.com.nanfeng.securitytest.smscode.SmsAuthenticationConfig;
import cn.com.nanfeng.securitytest.smscode.SmsCode;
import cn.com.nanfeng.securitytest.smscode.SmsCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-10-19 13:56
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private MyAuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private ValidateCodeFilter validateCodeFilter;

    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SmsCodeFilter smsCodeFilter;

    @Autowired
    private SmsAuthenticationConfig  smsAuthenticationConfig;

    @Autowired
    private MySessionExpiredStrategy sessionExpiredStrategy;



    @Override
    public void configure(HttpSecurity http) throws Exception{
        http//.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class) // 添加验证码校验过滤器
            //.addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)
            .formLogin()//表单方式
            //httpBasic()
            .loginPage("/authentication/require")//跳转到登录页面的请求URL
            .loginProcessingUrl("/login")//对应登录页面form表单的action="/login"
            .successHandler(authenticationSuccessHandler)
            .failureHandler(authenticationFailureHandler)
            .and()
            /*.rememberMe()
            .tokenRepository(persistentTokenRepository())//配置token持久化仓库
            .tokenValiditySeconds(3600)
            .userDetailsService(userDetailsService)//处理自动登录逻辑
            .and()*/
            .authorizeRequests()//授权方式
            .antMatchers("/authentication/require","/static/**",
                    "/code/image","/code/sms","/session/invalid").permitAll()//表示跳转到登录页面的请求不被拦截，否则会进入无限循环。
            .anyRequest()//所有请求
            .authenticated()//都需要认证
            .and()
            .sessionManagement()//添加Session管理器
            .invalidSessionUrl("/session/invalid")//Session失效后跳转到这个连接
            .maximumSessions(1)
            .maxSessionsPreventsLogin(true)
            .expiredSessionStrategy(sessionExpiredStrategy)
            .and()
            .and()
            .csrf().disable();
            //.apply(smsAuthenticationConfig);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        jdbcTokenRepository.setCreateTableOnStartup(false);
        return jdbcTokenRepository;
    }


}
