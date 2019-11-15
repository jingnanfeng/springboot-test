package cn.com.nanfeng.rabbitmqtest.simple;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-11-14 9:37
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleTests {

    @Resource
    private SimpleSender simpleSender;

    @Test
    public void simpleSend(){
        simpleSender.send();
    }
}
