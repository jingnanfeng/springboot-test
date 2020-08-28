package cn.com.nanfeng.boot;

import cn.com.nanfeng.boot.constants.GlobalConstant;
import cn.com.nanfeng.boot.consumer.ConsumerDemo;
import cn.com.nanfeng.boot.model.Fsource;
import cn.com.nanfeng.boot.serializer.JsonSerializer;
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

    private static Fsource fsource1 = null;
    private static Fsource fsource2 = null;


    @BeforeClass
    public static void initObject(){

        fsource1 = new Fsource("1","55","44",System.currentTimeMillis());
        fsource2 = new Fsource("1","66","44",System.currentTimeMillis());

    }


    @Test
    public void testSendMessage(){
        byte[] data = serializer.toJSONBytes(fsource1);
        kafkaTemplate.send(GlobalConstant.FSOURCE,data);
    }

}
