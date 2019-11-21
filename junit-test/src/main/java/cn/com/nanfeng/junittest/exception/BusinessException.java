package cn.com.nanfeng.junittest.exception;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-11-21 11:50
 */
public class BusinessException extends RuntimeException {

    private int code;

    public BusinessException(){
    }

    public BusinessException(String message){
        super(message);
    }

    public BusinessException(int code ,String message){
        super(message);
        this.code = code;
    }


}
