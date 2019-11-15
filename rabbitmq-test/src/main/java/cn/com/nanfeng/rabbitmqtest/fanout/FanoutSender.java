package cn.com.nanfeng.rabbitmqtest.fanout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-11-14 14:07
 */
@Component
public class FanoutSender {

    private Logger logger = LoggerFactory.getLogger(FanoutSender.class);

    @Resource
    private AmqpTemplate template;

    public void fanoutSend(){
        String content = "这个一次发布模式的消息队列";
        logger.info("fanoutSend:"+content);
        template.convertAndSend("fanoutExchange","",content);
    }

}
