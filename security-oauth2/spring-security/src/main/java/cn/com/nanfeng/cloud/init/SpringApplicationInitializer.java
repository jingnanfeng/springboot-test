package cn.com.nanfeng.cloud.init;

import cn.com.nanfeng.cloud.config.ApplicationConfig;
import cn.com.nanfeng.cloud.config.WebConfig;
import cn.com.nanfeng.cloud.config.WebSecurityConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2020-07-08 14:03
 */
public class SpringApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * 加载spring容器,相当于加载application.xml
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{ApplicationConfig.class, WebSecurityConfig.class};
    }

    /**
     * servletContext,相当于加载soringmvc.xml
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    /**
     * url-mapping
     * @return
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
