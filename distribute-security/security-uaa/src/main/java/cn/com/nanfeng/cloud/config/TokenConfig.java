package cn.com.nanfeng.cloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2020-07-14 9:05
 */
@Configuration
public class TokenConfig {

    public static final String SIGNINE_KEY = "uaa123";

    /**
     * 令牌的存储方式
     */
    @Bean
    public TokenStore tokenStore(){
        //JWT令牌存储方案
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        //对称密钥，资源服务器使用该密钥来验证
        converter.setSigningKey(SIGNINE_KEY);
        return converter;
    }
}
