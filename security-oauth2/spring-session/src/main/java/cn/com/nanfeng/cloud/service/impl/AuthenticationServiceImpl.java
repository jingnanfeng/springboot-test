package cn.com.nanfeng.cloud.service.impl;

import cn.com.nanfeng.cloud.model.AuthenticationRequest;
import cn.com.nanfeng.cloud.model.UserDto;
import cn.com.nanfeng.cloud.service.IAuthenticationService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2020-07-08 17:17
 */
@Service
public class AuthenticationServiceImpl implements IAuthenticationService {


    @Override
    public UserDto authentication(AuthenticationRequest authenticationRequest) {
        if (authenticationRequest == null
                || StringUtils.isEmpty(authenticationRequest.getUsername())
                || StringUtils.isEmpty(authenticationRequest.getPassword())){
            throw new RuntimeException("账号或密码为空");
        }
        //查询用户信息
        UserDto userDto = getUserDto(authenticationRequest.getUsername());
        if (userDto == null){
            throw new RuntimeException("查询不到该用户");
        }
        if (!authenticationRequest.getPassword().equals(userDto.getPassword())){
            throw new RuntimeException("账号或密码错误");
        }
        return userDto;
    }

    /**
     * 模拟用户查询
     * @param username
     * @return
     */
    private UserDto getUserDto(String username){
        return userMap.get(username);
    }

    /**
     * 用户信息
     */
    private Map<String,UserDto> userMap = new HashMap<>();
    {
        Set<String> authorities1 = new HashSet<>();
        //p1和getUser对应
        authorities1.add("p1");
        Set<String> authorities2 = new HashSet<>();
        //p2和getUserDetail对应
        authorities2.add("p2");

        userMap.put("zhangsan",new UserDto("1001","zhangsan","123","张三","133443",authorities1));
        userMap.put("lisi",new UserDto("1002","lisi","456","李四","144553",authorities2));
    }

}
