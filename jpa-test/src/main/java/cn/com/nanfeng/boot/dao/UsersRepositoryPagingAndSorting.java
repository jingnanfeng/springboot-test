package cn.com.nanfeng.boot.dao;

import cn.com.nanfeng.boot.model.po.Users;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author liutao
 * @date 2020-08-19 16:09
 */
public interface UsersRepositoryPagingAndSorting extends PagingAndSortingRepository<Users,Integer> {
}
