package cn.com.nanfeng.boot.dao;

import cn.com.nanfeng.boot.model.po.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 基于@Query查询与更新
 * @author liutao
 * @date 2020-08-19 14:58
 */
public interface UserRepositoryQueryAnnotation extends JpaRepository<Users,Integer> {

    @Query("from Users where name = ?1")
    List<Users> queryByNameUseHQL(String name);

    @Query(value = "select * from users where name = ?1",nativeQuery = true)
    List<Users> queryByNameUseSQL(String name);

    @Query("update Users set name=?1 where id=?2")
    @Modifying
    void updateUsersNameById(String name,Integer id);

}
