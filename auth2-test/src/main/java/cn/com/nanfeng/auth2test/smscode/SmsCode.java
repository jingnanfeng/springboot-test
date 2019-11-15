package cn.com.nanfeng.auth2test.smscode;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-10-23 18:41
 */
public class SmsCode {

    private String code;

    public SmsCode(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
