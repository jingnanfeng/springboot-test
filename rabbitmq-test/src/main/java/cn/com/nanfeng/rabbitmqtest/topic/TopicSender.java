package cn.com.nanfeng.rabbitmqtest.topic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-11-15 11:34
 */
@Component
public class TopicSender {

    private static final Logger logger = LoggerFactory.getLogger(TopicSender.class);

    @Resource
    private AmqpTemplate template;

    public void topicSendOne(){
        String content = "发送第一个消息";
        logger.info("topicSendOne:[{}]",content);
        template.convertAndSend("topicExchange","topic.message",content);
        logger.info("topicSendOne：发送消息结束");
    }

    public void topicSendTwo(){
        String content = "发送第二个消息";
        logger.info("topicSendTwo:[{}]",content);
        template.convertAndSend("topicExchange","topic.messages",content);
        logger.info("topicSendTwo：发送消息结束");
    }

    public void topicSendThree(){
        String content = "发送第三个消息";
        logger.info("topicSendThree:[{}]",content);
        template.convertAndSend("topicExchange","topic.aa",content);
        logger.info("topicSendThree：发送消息结束");
    }

}
