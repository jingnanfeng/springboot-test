package cn.com.nanfeng.junittest.util;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serializable;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-11-27 14:57
 */
@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Wrapper<T> implements Serializable {

    /**
     * 编号
     */
    private int code;
    /**
     * 信息
     */
    private String message;
    /**
     * 结果数据
     */
    private T result;

    public Wrapper(){

    }

    public Wrapper(int code,String message){
        this(code,message,null);
    }

    public Wrapper(int code,String message,T result){
        super();
        this.code(code).message(message).result(result);
    }

    private Wrapper<T> code(int code){
        this.setCode(code);
        return this;
    }

    private Wrapper<T> message(String message){
        this.setMessage(message);
        return this;
    }

    public Wrapper<T> result(T result){
        this.setResult(result);
        return this;
    }

}
