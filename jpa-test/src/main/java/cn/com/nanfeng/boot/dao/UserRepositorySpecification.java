package cn.com.nanfeng.boot.dao;

import cn.com.nanfeng.boot.model.po.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author liutao
 * @date 2020-08-20 9:37
 */
public interface UserRepositorySpecification extends JpaRepository<Users,Integer>, JpaSpecificationExecutor<Users> {
}
