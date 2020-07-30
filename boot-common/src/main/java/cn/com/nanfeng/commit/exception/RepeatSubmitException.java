package cn.com.nanfeng.commit.exception;

/**
 * @author liutao
 * @title RepeatSubmitException
 * @description 重复提交异常
 * @date 2020-07-29 17:26
 */
public class RepeatSubmitException extends RuntimeException {

    /**
     * code
     */
    private int code;

    public RepeatSubmitException(int code,String msg){
        super(msg);
        this.code  = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
