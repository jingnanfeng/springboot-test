package cn.com.nanfeng.filterandint.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-12-09 16:30
 */
/*@Component
@WebFilter(urlPatterns = {"/*"})*/
public class TimeFilter implements Filter {

    private static Logger logger = LoggerFactory.getLogger(TimeFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException{
        logger.info(">>>>>>>>>>>>>>>>>>过滤器初始化<<<<<<<<<<<<<<<<<<<<");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info(">>>>>>>>>>>>>>>>>过滤器开始执行<<<<<<<<<<<<<<<<<<<<");
        Long start = System.currentTimeMillis();
        chain.doFilter(request,response);
        logger.info(">>>>>>>>>>>>>>>>>【过滤器】耗时：[{}]<<<<<<<<<<<<<<<<<",System.currentTimeMillis() - start);
        logger.info(">>>>>>>>>>>>>>>>>>过滤器执行结束<<<<<<<<<<<<<<<<<<<");
    }

    @Override
    public void destroy(){
        logger.info(">>>>>>>>>>>>>>>过滤器销毁<<<<<<<<<<<<<<<<<<<<<");
    }
}
