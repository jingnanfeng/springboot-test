package cn.com.nanfeng.junittest.model.po;

import lombok.Data;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-11-15 15:12
 */
@Data
public class User {

    private Integer id;

    private String name;

    public User(){

    }

    public User(Integer id,String name){
        this.id = id;
        this.name = name;
    }

}
