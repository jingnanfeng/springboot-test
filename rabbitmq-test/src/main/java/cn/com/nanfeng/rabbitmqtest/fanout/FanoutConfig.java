package cn.com.nanfeng.rabbitmqtest.fanout;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-11-14 10:58
 */
@Configuration
public class FanoutConfig {

    private static final String MESSAGEA = "message.A";

    private static final String MESSAGEB = "message.B";

    private static final String MESSAGEC = "message.C";


    @Bean
    public Queue messageA(){
        return new Queue(MESSAGEA);
    }

    @Bean
    public Queue messageB(){
        return new Queue(MESSAGEB);
    }

    @Bean Queue messageC(){
        return new Queue(MESSAGEC);
    }


    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    public Binding bindingExchangeMessageA(Queue messageA,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(messageA).to(fanoutExchange);
    }

    @Bean
    public Binding bindingExchangeMessageB(Queue messageB,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(messageB).to(fanoutExchange);
    }
    @Bean
    public Binding bindingExchangeMessageC(Queue messageC,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(messageC).to(fanoutExchange);
    }

}
