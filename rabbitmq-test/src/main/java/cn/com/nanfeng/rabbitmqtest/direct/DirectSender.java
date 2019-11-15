package cn.com.nanfeng.rabbitmqtest.direct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-11-14 15:24
 */
@Component
public class DirectSender {

    private Logger logger = LoggerFactory.getLogger(DirectSender.class);

    @Resource
    private AmqpTemplate template;

    public void directSendOne(){
        String context = "发送第一个直接路由的消息";
        logger.info("directSenderOne:[{}]",context);
        template.convertAndSend("directExchange","messageD",context);
    }

    public void directSendTwo(){
        String context = "发送第二个直接路由的消息";
        logger.info("directSenderTwo:[{}]",context);
        template.convertAndSend("directExchange","messageA",context);
    }

    public void directSendThree(){
        String context = "发送的第三个直接路由的消息";
        logger.info("directSendThree:[{}]",context);
        template.convertAndSend("directExchange","messageB",context);
    }

}
