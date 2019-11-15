package cn.com.nanfeng.junittest.user;

import cn.com.nanfeng.junittest.model.po.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-11-15 15:10
 */
@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/user/getUser/{id}")
    public User getUser(@PathVariable("id")String id){
        User user = new User(1111,"小明");
        logger.info("id=[{}];name=[{}]",1111,"小明");
        return user;
    }

    @PostMapping("/user/addUser")
    public String addUser(@RequestBody User user){
        logger.info("id=[{}];name=[{}]",user.getId(),user.getName());
        return "添加成功";
    }

}
