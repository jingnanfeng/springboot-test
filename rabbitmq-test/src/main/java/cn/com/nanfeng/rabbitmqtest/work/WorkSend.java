package cn.com.nanfeng.rabbitmqtest.work;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-11-14 9:59
 */
@Component
public class WorkSend {

    private Logger logger = LoggerFactory.getLogger(WorkSend.class);

    @Resource
    private AmqpTemplate template;

    public void workSend(int i){
        String context = "第"+ i +"发送消息";
        logger.info("work模式："+context);
        template.convertAndSend("work",context);
    }

}
