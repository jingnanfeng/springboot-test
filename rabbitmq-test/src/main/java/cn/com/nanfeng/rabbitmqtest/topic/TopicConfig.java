package cn.com.nanfeng.rabbitmqtest.topic;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-11-15 11:15
 */
@Configuration
public class TopicConfig {

    private static final String MESSAGE = "message";
    private static final String MESSAGES = "messages";

    @Bean
    public Queue messageQueue(){
        return new Queue(MESSAGE);
    }

    @Bean
    public Queue messagesQueue(){
        return new Queue(MESSAGES);
    }

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange("topicExchange");
    }

    @Bean
    public Binding bindingMessageQueue(Queue messageQueue,TopicExchange topicExchange){
        return BindingBuilder.bind(messageQueue).to(topicExchange).with("topic.message");
    }

    @Bean
    public Binding bindingMessagesQueue(Queue messagesQueue,TopicExchange topicExchange){
        return BindingBuilder.bind(messagesQueue).to(topicExchange).with("topic.#");
    }


}
