package cn.com.nanfeng.redistest.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-11-07 9:24
 */
@Data
public class UserEntity implements Serializable {
    private static final long serialVersionUID = -540466378857976528L;

    private Long id;
    private String guid;
    private String name;
    private String age;
    private Date createTime;

}
