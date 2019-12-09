package cn.com.nanfeng.junittest.util;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-11-27 15:31
 */
public class WrapMapper {

    private WrapMapper(){

    }

    public static <E> Wrapper<E> wrap(int code,String message,E o){
        return new Wrapper<>(code,message,o);
    }

    public static <E> Wrapper<E> wrap(int code,String message){
        return wrap(code,message,null);
    }
}
