package cn.com.nanfeng.boot.model.vo;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2020-05-10 10:27
 */
@Data
@Builder(toBuilder = true)
public class BookVO {

    private Integer bId;

    private String bName;

    private String bContent;

    private String bImage;

    private Integer bPrice;

    private Integer bNumber;

    private Date bDate;

    /*public static class Builder{
        private Integer bId;

        private String bName;

        private String bContent;

        private String bImage;

        private Integer bPrice;

        private Integer bNumber;

        private Date bDate;

        public Builder(Integer bId,String bName){
            this.bId = bId;
            this.bName = bName;
        }

        public Builder bContent(String bContent){
            this.bContent = bContent;
            return this;
        }
        public Builder bImage(String bImage){
            this.bImage = bImage;
            return this;
        }
        public Builder bPrice(Integer bPrice){
            this.bPrice = bPrice;
            return this;
        }
        public Builder bNumber(Integer bNumber){
            this.bNumber = bNumber;
            return this;
        }

        public Builder bDate(Date bDate){
            this.bDate = bDate;
            return this;
        }

        public BookVO build(){
            return new BookVO(this);
        }
    }
    private BookVO(Builder builder){
        bId = builder.bId;
        bName = builder.bName;
        bContent = builder.bContent;
        bImage = builder.bImage;
        bNumber = builder.bNumber;
        bPrice = builder.bPrice;
        bDate = builder.bDate;
    }
*/
}
