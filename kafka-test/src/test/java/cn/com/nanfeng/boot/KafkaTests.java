package cn.com.nanfeng.boot;

import cn.com.nanfeng.boot.constants.GlobalConstant;
import cn.com.nanfeng.boot.consumer.ConsumerDemo;
import cn.com.nanfeng.boot.model.Fsource;
import cn.com.nanfeng.boot.model.SingleMessage;
import cn.com.nanfeng.boot.serializer.JsonSerializer;
import com.alibaba.fastjson.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author liutao
 * @date 2020-08-27 16:04
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class KafkaTests {

    @Resource
    private KafkaTemplate<String,Object> kafkaTemplate;

    private JsonSerializer<Fsource> serializer = new JsonSerializer<>();






    @Test
    public void testSendMessage(){

        long timeMillis = System.currentTimeMillis();
        SingleMessage singleMessage = new SingleMessage();
        singleMessage.setTimeLong(timeMillis);
        singleMessage.setName("zhangsan");
        String json = JSONObject.toJSONString(singleMessage);
        kafkaTemplate.send(GlobalConstant.TOPIC,json);
    }

}
