package cn.com.nanfeng.rabbitmqtest.fanout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-11-14 14:12
 */
@Component
@RabbitListener(queues = "message.C")
public class FanoutReceiverThree {

    private static final Logger logger = LoggerFactory.getLogger(FanoutReceiverThree.class);

    @RabbitHandler
    public void fanoutReceiver(String arg){
        logger.info("fanoutReceiverThree:[{}]",arg);
    }
}
