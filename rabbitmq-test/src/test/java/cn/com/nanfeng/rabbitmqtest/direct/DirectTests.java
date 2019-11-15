package cn.com.nanfeng.rabbitmqtest.direct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-11-15 11:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DirectTests {

    @Resource
    private DirectSender directSender;

    @Test
    public void testDirect(){
        directSender.directSendOne();
        directSender.directSendTwo();
        directSender.directSendThree();
    }

}
