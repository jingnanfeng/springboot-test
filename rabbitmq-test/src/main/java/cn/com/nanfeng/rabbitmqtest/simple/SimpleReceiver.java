package cn.com.nanfeng.rabbitmqtest.simple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-11-14 9:44
 */
@Component
@RabbitListener(queues = "simple")
public class SimpleReceiver {

    private Logger logger = LoggerFactory.getLogger(SimpleReceiver.class);

    @RabbitHandler
    public void process(String hello){
        logger.info("receiver:"+hello);
    }
}
