package cn.com.nanfeng.rabbitmqtest.work;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-11-14 9:58
 */
@Configuration
public class WorkConfig {

    @Bean
    public Queue workQueue(){
        return new Queue("work");
    }
}
