package cn.com.nanfeng.boot;

import cn.com.nanfeng.boot.service.MapReduceService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author liutao
 * @date 2020-08-25 9:30
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ReduceTests {

    @Resource
    private MapReduceService mapReduceService;


    @Test
    public void testWordCount() throws Exception{
        mapReduceService.wordCount("output","hdfs://hadoop1:9000/root/nanfeng/input/a.txt");
    }

}
