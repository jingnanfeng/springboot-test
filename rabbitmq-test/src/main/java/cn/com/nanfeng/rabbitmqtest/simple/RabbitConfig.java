package cn.com.nanfeng.rabbitmqtest.simple;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-11-14 9:38
 */
@Configuration
public class RabbitConfig {

    @Bean
    public Queue queue(){
        return new Queue("simple");
    }
}
