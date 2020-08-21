package cn.com.nanfeng.boot.dao;

import cn.com.nanfeng.boot.model.po.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * <T ,ID> T需要映射的实体，ID当前映射的实体中的OID的类型
 * @author liutao
 * @date 2020-08-19 14:23
 */
public interface UsersRepository extends JpaRepository<Users,Integer> {

    /**
     * 通过名字查询
     * @param name
     * @return
     */
    List<Users> findByName(String name);

    /**
     * 通过名字和年龄查询
     * @param name
     * @param age
     * @return
     */
    List<Users> findByNameAndAge(String name,Integer age);

    /**
     * 通过名字模糊查询
     * @param name
     * @return
     */
    List<Users> findByNameLike(String name);

}
