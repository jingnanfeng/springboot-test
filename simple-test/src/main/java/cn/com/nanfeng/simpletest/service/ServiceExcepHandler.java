package cn.com.nanfeng.simpletest.service;

import cn.com.nanfeng.commit.exception.BusinessException;
import cn.com.nanfeng.commit.exception.ErrorCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2020-05-10 14:01
 */
@Component
@Aspect
@Slf4j
public class ServiceExcepHandler {

    @Around("@annotation(cn.com.nanfeng.simpletest.annotation.ServiceExcepCatch) || @within(cn.com.nanfeng.simpletest.annotation.ServiceExcepCatch)")
    public void serviceExcepHandler(ProceedingJoinPoint joinPoint){
        try {

        }catch (DataAccessException e){
            e.printStackTrace();
            throw new BusinessException(ErrorCodeEnum.ERROR30001.getCode(),ErrorCodeEnum.ERROR30001.getMessage());
        }catch (BusinessException e){
            e.printStackTrace();
            throw e;
        }catch (Exception e){
            throw new BusinessException(ErrorCodeEnum.ERROR50001.getCode(),ErrorCodeEnum.ERROR50001.getMessage());
        }
    }
}
