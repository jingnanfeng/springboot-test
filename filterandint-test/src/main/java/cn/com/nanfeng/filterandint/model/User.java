package cn.com.nanfeng.filterandint.model;

import lombok.Data;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-12-09 17:05
 */
@Data
public class User {

    private Long id;

    private String name;

    public User(){}

    public User(Long id,String name){
        this.id = id;
        this.name = name;
    }
}
