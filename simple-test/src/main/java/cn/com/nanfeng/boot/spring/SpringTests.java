package cn.com.nanfeng.boot.spring;

import cn.com.nanfeng.boot.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2020-06-17 19:02
 */
public class SpringTests {

    public static void main(String[] args) {
        //初始化spring上下文
        //bean存放位置--map--单例池 map singleobjects = new HashMap()
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(AppConfig.class);
        System.out.println(ac.getBean(BookService.class));
        GenericBeanDefinition definition = new GenericBeanDefinition();
        definition.setBeanClass(BookService.class);
    }

}
