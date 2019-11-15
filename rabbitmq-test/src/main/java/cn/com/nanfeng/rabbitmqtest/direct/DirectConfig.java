package cn.com.nanfeng.rabbitmqtest.direct;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-11-14 15:08
 */
@Configuration
public class DirectConfig {

    private static final String MESSAGEA = "messageA";
    private static final String MESSAGEB = "messageB";
    private static final String MESSAGEC = "messageC";

    @Bean
    public Queue messageAQueue(){
        return new Queue(MESSAGEA);
    }
    @Bean
    public Queue messageBQueue(){
        return new Queue(MESSAGEB);
    }
    @Bean
    public Queue messageCQueue(){
        return new Queue(MESSAGEC);
    }
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("directExchange");
    }

    @Bean
    public Binding bindingMessageAQueue(Queue messageAQueue, DirectExchange exchange){
        return BindingBuilder.bind(messageAQueue).to(exchange).with("messageA");
    }
    @Bean
    public Binding bindingMessageBQueue(Queue messageBQueue,DirectExchange exchange){
        return BindingBuilder.bind(messageBQueue).to(exchange).with("messageB");
    }

    @Bean
    public Binding bindingMessageCQueue(Queue messageCQueue,DirectExchange exchange){
        return BindingBuilder.bind(messageCQueue).to(exchange).with("messageD");
    }


}
