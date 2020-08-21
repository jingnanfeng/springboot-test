package cn.com.nanfeng.boot.model.po;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * user表实体类
 * @author liutao
 * @date 2020-08-19 14:05
 */
@Data
@Entity
@Table(name = "users")
@ToString
public class Users {

    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * name
     */
    @Column(name = "name")
    private String name;

    /**
     * password
     */
    @Column(name = "password")
    private String password;

    /**
     * age
     */
    @Column(name = "age")
    private Integer age;

    /**
     * sex
     */
    @Column(name = "sex")
    private String sex;

    /**
     * phone
     */
    @Column(name = "phone")
    private String phone;

    /**
     * email
     */
    @Column(name = "email")
    private String email;

}
