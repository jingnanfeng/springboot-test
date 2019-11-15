package cn.com.nanfeng.rabbitmqtest.work;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-11-14 10:03
 */
@Component
@RabbitListener(queues = "work")
public class WorkOneReceiver {

    private static final Logger logger = LoggerFactory.getLogger(WorkOneReceiver.class);

    @RabbitHandler
    public void receiver(String arg){
        logger.info("接收者1："+arg);
    }
}
