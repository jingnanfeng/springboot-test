package cn.com.nanfeng.securitytest.config;

import cn.com.nanfeng.securitytest.model.MyUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-10-19 16:17
 */
@Configuration
public class MyUserDetailService  implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(MyUserDetailService.class);
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //模拟一个用户，代替数据库获取逻辑
        MyUser user = new MyUser();
        user.setUsername(username);
        //密码加密
        String password = passwordEncoder.encode("123456");
        user.setPassword(password);

        logger.info("输出加密后的密码{}",password);

        return new User(username,user.getPassword(),user.isEnable(),
                user.isAccountNonEcpired(),user.isCredentialsNonExpired(),
                user.isAccountNonLoaked(), AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));


    }
}
