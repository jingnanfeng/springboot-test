package cn.com.nanfeng.boot.dao;

import cn.com.nanfeng.boot.model.po.Users;
import org.springframework.data.repository.CrudRepository;

/**
 * @author liutao
 * @date 2020-08-19 15:52
 */
public interface UsersCrudRepository extends CrudRepository<Users,Integer> {
}
