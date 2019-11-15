package cn.com.nanfeng.rabbitmqtest.topic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-11-15 11:50
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TopicTests {

    @Resource
    private TopicSender topicSender;

    @Test
    public void testTopic(){
        topicSender.topicSendOne();
        topicSender.topicSendTwo();
        topicSender.topicSendThree();
    }

}
