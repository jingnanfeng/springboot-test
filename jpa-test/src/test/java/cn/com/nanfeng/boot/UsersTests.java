package cn.com.nanfeng.boot;

import cn.com.nanfeng.boot.dao.UserRepositoryQueryAnnotation;
import cn.com.nanfeng.boot.dao.UsersCrudRepository;
import cn.com.nanfeng.boot.dao.UsersRepository;
import cn.com.nanfeng.boot.dao.UsersRepositoryPagingAndSorting;
import cn.com.nanfeng.boot.model.po.Users;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

/**
 * @author liutao
 * @date 2020-08-19 14:27
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UsersTests {

    @Resource
    private UsersRepository usersRepository;
    @Resource
    private UserRepositoryQueryAnnotation userRepositoryQueryAnnotation;
    @Resource
    private UsersCrudRepository usersCrudRepository;
    @Resource
    private UsersRepositoryPagingAndSorting usersRepositoryPagingAndSorting;


    @Test
    public void testSave(){
        Users users = new Users();
        users.setEmail("liu05265218@163.com");
        users.setName("zhangsan");
        users.setPassword("123456");
        users.setPhone("18235484807");
        usersRepository.save(users);
    }

    @Test
    public void testFindBy(){
        List<Users> usersList = usersRepository.findByName("nanfeng");
        log.info(">>>>>>>>>>>>>>>>>通过名字查询<<<<<<<<<<<<<<<<<<<<<");
        for (Users users : usersList) {
            log.info(users.toString());
        }
        List<Users> usersList1 = usersRepository.findByNameAndAge("nanfeng", 23);
        log.info(">>>>>>>>>>>>>>>>>>通过名字和年龄查询<<<<<<<<<<<<<<<<<<<");
        for (Users users : usersList1) {
            log.info(users.toString());
        }
        List<Users> usersList2 = usersRepository.findByNameLike("%n%");
        log.info(">>>>>>>>>>>>>>>>>>通过名字模糊查询<<<<<<<<<<<<<<<<<<<<<");
        for (Users users : usersList2) {
            log.info(users.toString());
        }
    }

    @Test
    @Transactional
    @Rollback(false)
    public void testQuery(){
        List<Users> usersList = userRepositoryQueryAnnotation.queryByNameUseHQL("zhangsan");
        log.info(">>>>>>>>>>>>>>>>>HQL查询<<<<<<<<<<<<<<<<<<<<<");
        for (Users users : usersList) {
            log.info(users.toString());
        }
        List<Users> usersList1 = userRepositoryQueryAnnotation.queryByNameUseSQL("zhangsan");
        log.info(">>>>>>>>>>>>>>>>>SQL查询<<<<<<<<<<<<<<<<<<<<<");
        for (Users users : usersList) {
            log.info(users.toString());
        }
        userRepositoryQueryAnnotation.updateUsersNameById("lisi",3);
    }

    @Test
    public void testCrudRepositorySave(){
        Users users = new Users();
        users.setEmail("nanfeng@163.com");
        users.setName("wangwu");
        users.setPassword("563");
        users.setPhone("1123513256");
        usersCrudRepository.save(users);
    }

    @Test
    public void testCrudRepositoryUpdate(){
        Users users = new Users();
        users.setId(4);
        users.setEmail("nanfeng@163.com");
        users.setName("wangwu");
        users.setPassword("5625");
        users.setPhone("1123513256");
        usersCrudRepository.save(users);
    }

    @Test
    public void testFindOne(){
        Optional<Users> users = usersCrudRepository.findById(4);
        log.info(users.get().toString());
        List<Users> list = (List<Users>)usersCrudRepository.findAll();
        for (Users user : list) {
            log.info(user.toString());
        }
    }

    @Test
    public void testCrudRepositoryDelete(){
        usersCrudRepository.deleteById(4);
    }

    @Test
    public void testPagingAndSortingRepositorySory(){
        //Sort对象封装了排序的规则
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        List<Users> list = (List<Users>)usersRepositoryPagingAndSorting.findAll(sort);
        for (Users users : list) {
            log.info(users.toString());
        }
    }

    @Test
    public void testPagingAndSortingRepositoryPaging(){
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        //Pageable封装了分页的参数，当前页，每页显示的条数
        //PageRequest(page.size)page表示当前页，size表示每页显示多少条
        Pageable pageable = PageRequest.of(0, 2, sort);
        Page<Users> page = usersRepositoryPagingAndSorting.findAll(pageable);
        log.info("数据的总条数：[{}]",page.getTotalElements());
        log.info("数据的总页数:[{}]",page.getTotalPages());
        List<Users> list = page.getContent();
        for (Users users : list) {
            log.info(users.toString());
        }
    }

    /**
     * JpaSpecificationExecutor
     */
    @Test
    public void testJpaSpecificationExecutor(){
        /**
         * Specification:用于封装查询条件
         */
        Specification<Users> spec = new Specification<Users>() {
            //Predicate封装了单个查询

            /**
             *
             * @param root 查询对象属性的封装
             * @param criteriaQuery 封装了我们要执行的查询中的各个比分的信息 select from order
             * @param criteriaBuilder 查询条件的构造器
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Users> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                /**
                 * 参数一：查询的属性
                 * 参数二：条件的值
                 */
                Predicate predicate = criteriaBuilder.equal(root.get("name"), "张三");
                return predicate;

            }
        }
    }

}
