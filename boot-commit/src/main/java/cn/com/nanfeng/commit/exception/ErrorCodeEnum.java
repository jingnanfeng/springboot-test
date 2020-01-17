package cn.com.nanfeng.commit.exception;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-12-23 22:23
 */
public enum ErrorCodeEnum {

    ERROR10001(10001,"参数错误"),
    ;

    private int code;

    private String message;

    ErrorCodeEnum(int code,String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
