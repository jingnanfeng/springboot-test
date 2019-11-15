package cn.com.nanfeng.rabbitmqtest.direct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-11-15 9:23
 */
@Component
@RabbitListener(queues = "messageB")
public class DirectReceiverTwo {

    private static final Logger logger = LoggerFactory.getLogger(DirectReceiverTwo.class);

    @RabbitHandler
    public void receiver(String arg){
        logger.info("接收者two:[{}]",arg);
    }
}
