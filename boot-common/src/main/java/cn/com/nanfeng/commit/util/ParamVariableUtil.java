package cn.com.nanfeng.commit.util;

import cn.com.nanfeng.commit.exception.BusinessException;
import cn.com.nanfeng.commit.exception.ErrorCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-12-23 22:13
 */
public class ParamVariableUtil {

    private static final Logger logger =  LoggerFactory.getLogger(ParamVariableUtil.class);

    public static void StringParam(String str,String msg){
        if (str == null || "".equals(str.trim())){
            logger.error(msg);
            throw new BusinessException(ErrorCodeEnum.ERROR10001.getCode(),msg);
        }
    }

    public static void intParam(Integer integer,String msg){
        if (integer == null){
            logger.error(msg);
            throw new BusinessException(ErrorCodeEnum.ERROR10001.getCode(),msg);
        }
    }

    public static void ListParam(List<?> list,String msg){
        if (list ==null || list.size() == 0){
            logger.error(msg);
            throw new BusinessException(ErrorCodeEnum.ERROR10001.getCode(),msg);
        }
    }
}
