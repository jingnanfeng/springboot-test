package cn.com.nanfeng.cloud.dao;

import cn.com.nanfeng.cloud.model.PermissionDto;
import cn.com.nanfeng.cloud.model.UserDto;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2020-07-10 18:24
 */
@Repository
public class UserDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     * 根据账号查询用户信息
     * @param username
     * @return
     */
    public UserDto getUserByUsername(String username){
        String sql = "select * from t_user where username = ?";
        //连接数据库查询用户
        List<UserDto> userDtoList = jdbcTemplate.query(sql, new Object[]{username}, new BeanPropertyRowMapper<>(UserDto.class));
        if (userDtoList != null && userDtoList.size() == 1){
            return userDtoList.get(0);
        }
        return null;
    }

    /**
     * 根据用户id查询用户权限
     * @param userId
     * @return
     */
    public List<String> findPermissionsByUserId(String userId){
        String sql = "select * from t_permission where id in " +
                "(select permission_id from t_role_permission where role_id in " +
                "(select role_id from t_user_role where user_id = ?))";

        List<PermissionDto> permissionDtoList = jdbcTemplate.query(sql, new Object[]{userId},
                new BeanPropertyRowMapper<>(PermissionDto.class));
        List<String> list = new ArrayList<>();
        permissionDtoList.forEach(permissionDto -> list.add(permissionDto.getCode()));
        return list;
    }
}
