package cn.com.nanfeng.junittest.exception;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-11-21 11:55
 */
public enum ErrorCodeEnum {


    B10001(10001,"参数错误"),
    ;



    private int code;
    private String message;

    public int getCode() {
        return code;
    }


    public String getMessage() {
        return message;
    }

    ErrorCodeEnum(int code,String message){
        this.code = code;
        this.message = message;
    }

    public static ErrorCodeEnum getEnum(int code){
        for (ErrorCodeEnum value : ErrorCodeEnum.values()) {
            if (value.code == code){
                return value;
            }
        }
        return null;
    }
}
