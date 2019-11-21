package cn.com.nanfeng.junittest.model.po;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * @author nanfeng
 * @date 2019/11/18
 */

@Data
public class Book {
    private Integer bId;

    @Length(min = 1,max = 10, message = "书名不规范")
    private String bName;

    private String bContent;

    private String bImage;

    private Integer bPrice;

    private Integer bNumber;

    private Date bDate;

}