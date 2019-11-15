package cn.com.nanfeng.rabbitmqtest.fanout;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-11-14 14:24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FanoutTests {

    @Resource
    private FanoutSender fanoutSender;

    @Test
    public void testFanout(){
        fanoutSender.fanoutSend();
    }

}
