package cn.com.nanfeng.rabbitmqtest.simple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-11-14 9:40
 */
@Component
public class SimpleSender {

    private Logger logger  = LoggerFactory.getLogger(SimpleSender.class);

    @Resource
    private AmqpTemplate template;

    public void send(){
        String context = "hello world";
        logger.info("simpleSender:" + context);
        template.convertAndSend("simple",context);

    }

}
