package cn.com.nanfeng.oracle_test.model.po;

import lombok.Data;

@Data
public class NfBook {
    private Long id;

    private String bookName;

    private String bookContent;

    private Long bookPrice;

    private Long bookNumber;

    private String auther;

    private String delFlag;

}