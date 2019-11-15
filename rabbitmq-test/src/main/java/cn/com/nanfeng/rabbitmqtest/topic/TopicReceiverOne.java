package cn.com.nanfeng.rabbitmqtest.topic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-11-15 11:44
 */
@Component
@RabbitListener(queues = "message")
public class TopicReceiverOne {

    private static final Logger logger = LoggerFactory.getLogger(TopicReceiverOne.class);

    @RabbitHandler
    public void receive(String arg){
        try {
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }
        logger.info("receiverOne:[{}]",arg);
    }
}
