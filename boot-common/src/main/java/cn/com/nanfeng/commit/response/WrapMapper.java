package cn.com.nanfeng.commit.response;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2020-05-10 11:48
 */
public class WrapMapper {

    private WrapMapper(){

    }

    /**
     * wrap
     * @param code
     * @param message
     * @param o
     * @param <E>
     * @return
     */
    public static <E> Wrapper<E> wrap(int code,String message, E o){
        return new Wrapper<>(code,message,o);
    }

    /**
     * wrap
     * @param code
     * @param message
     * @param <E>
     * @return
     */
    public static <E> Wrapper<E> wrap(int code,String message){
        return new Wrapper<>(code,message,null);
    }

    /**
     * 异常
     * @param e
     * @param <E>
     * @return
     */
    public static <E> Wrapper<E> wrap(Exception e){
        return new Wrapper<>(Wrapper.ERROR_CODE,e.getMessage());
    }

    /**
     * Wrap SUCCESS. code=200
     *
     * @param <E> the element type
     *
     * @return the wrapper
     */
    public static <E> Wrapper<E> ok() {
        return new Wrapper<>();
    }

    /**
     * Ok wrapper.
     *
     * @param <E> the type parameter
     * @param o   the o
     *
     * @return the wrapper
     */
    public static <E> Wrapper<E> ok(E o) {
        return new Wrapper<>(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, o);
    }

 }
