package cn.com.nanfeng.commit.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serializable;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2020-05-10 11:43
 */
@Data
public class Wrapper<T> implements Serializable {
    private static final long serialVersionUID = 3677547818313554789L;

    /**
     * 成功code
     */
    public static final int SUCCESS_CODE = 200;

    /**
     * 成功message
     */
    public static final  String SUCCESS_MESSAGE =  "操作成功";

    /**
     * 错误码.
     */
    public static final int ERROR_CODE = 500;

    /**
     * 编号
     */
    private int code;

    /**
     * 信息
     */
    private String message;

    /**
     * 结果
     */
    private T result;

    Wrapper(int code,String message){
        this(code,message,null);
    }
    Wrapper(int code,String message,T result){
        super();
        this.code(code).message(message).result(result);
    }
    Wrapper() {
        this(SUCCESS_CODE, SUCCESS_MESSAGE);
    }

    /**
     * 返回自身引用
     * @param code
     * @return
     */
    private Wrapper<T> code(int code){
        this.setCode(code);
        return this;
    }

    /**
     * 返回自身引用
     * @param message
     * @return
     */
    private Wrapper<T> message(String message){
        this.setMessage(message);
        return this;
    }

    /**
     * 返回自身引用
     * @param result
     * @return
     */
    public Wrapper<T> result(T result){
        this.setResult(result);
        return this;
    }
}
