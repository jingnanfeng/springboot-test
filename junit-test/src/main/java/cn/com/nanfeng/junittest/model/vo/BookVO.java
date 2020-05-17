package cn.com.nanfeng.junittest.model.vo;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2020-01-17 14:19
 */
@Data
@Builder
public class BookVO {

    private Integer bId;

    private String bName;

    private String bContent;

    private String bImage;

    private Integer bPrice;

    private Integer bNumber;

    private Date bDate;
}
