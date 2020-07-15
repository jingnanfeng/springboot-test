package cn.com.nanfeng.cloud.service;

import cn.com.nanfeng.cloud.dao.UserDao;
import cn.com.nanfeng.cloud.model.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2020-07-09 21:31
 */
@Service
@Slf4j
public class SpringDataUserDetailService implements UserDetailsService {

    @Resource
    private UserDao userDao;

    /**
     * 根据账号查询用户信息
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("username=[{}]",username);
        UserDto userDto = userDao.getUserByUsername(username);
        if (userDto == null){
            //如果用户查询不到，返回null，由provider来抛出异常
            return null;
        }
        List<String> permissions = userDao.findPermissionsByUserId(userDto.getId());
        //将permissions转成数组
        String[] permissionArray = new String[permissions.size()];
        permissions.toArray(permissionArray);
        UserDetails userDetails = User.withUsername(userDto.getUsername()).password(userDto.getPassword()).authorities(permissionArray).build();
        return userDetails;
    }


}
