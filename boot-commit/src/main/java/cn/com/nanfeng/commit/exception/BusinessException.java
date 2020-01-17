package cn.com.nanfeng.commit.exception;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-12-23 22:15
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -1484808731293311687L;
    /**
     * 异常码
     */
    protected int code;

    public BusinessException(){

    }

    public BusinessException(Throwable cause){
        super(cause);
    }
    public BusinessException(String massage){
        super(massage);
    }

    public BusinessException(int code,String message){
        super(message);
        this.code = code;
    }

    public BusinessException(String message,Throwable cause){
        super(message, cause);
    }

    public BusinessException(int code,String msgFormat,Object... args){
        super(String.format(msgFormat,args));
        this.code = code;
    }

    public BusinessException(ErrorCodeEnum codeEnum,Object... args){
        super(String.format(codeEnum.getMessage(),args));
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
