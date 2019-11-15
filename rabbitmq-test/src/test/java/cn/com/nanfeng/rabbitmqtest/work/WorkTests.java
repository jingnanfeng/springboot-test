package cn.com.nanfeng.rabbitmqtest.work;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-11-14 10:08
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WorkTests {

    private static final int LENGTH = 100;

    @Resource
    private WorkSend workSend;

    @Test
    public void testWork(){
        for (int i = 0; i < LENGTH; i++) {
            workSend.workSend(i);
        }
    }
}
