package cn.com.nanfeng.boot.model.po;

import lombok.Data;

import java.util.Date;

@Data
public class Student {
    private String studentId;

    private String name;

    private String identifyNo;

    private String profess;

    private String classes;

    private String enroTime;

    private String telNo;

    private Integer credit;


}