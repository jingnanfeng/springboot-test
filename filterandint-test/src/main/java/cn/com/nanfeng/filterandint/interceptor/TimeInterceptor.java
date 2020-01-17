package cn.com.nanfeng.filterandint.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-12-09 17:23
 */
@Component
public class TimeInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(TimeInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object o) throws Exception{
        logger.info(">>>>>>>>>>>>>>处理拦截之前<<<<<<<<<<<<<<<<<<<");
        request.setAttribute("startTime",System.currentTimeMillis());
        logger.info(((HandlerMethod)o).getBean().getClass().getName());
        logger.info(((HandlerMethod)o).getMethod().getName());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception{
        logger.info(">>>>>>>>>>>>>>>开始处理拦截<<<<<<<<<<<<<<<<<<");
        Long startTime = (Long) request.getAttribute("startTime");
        logger.info(">>>>>>>>>>>>拦截器耗时:[{}]",System.currentTimeMillis() - startTime);
    }

    @Override
    public void afterCompletion(HttpServletRequest request,HttpServletResponse response,Object o, Exception e){
        logger.info(">>>>>>>>>>>>>>>处理拦截之后<<<<<<<<<<<<<<<<<<<<<<<<");
        Long startTime = (Long) request.getAttribute("startTime");
        logger.info(">>>>>>>>>>>>>>拦截器耗时[{}]",System.currentTimeMillis()-startTime);
        logger.info(">>>>>>>>>>>异常信息：[{}]<<<<<<<<<<<<<<<<<<<<<",e);
    }

}
