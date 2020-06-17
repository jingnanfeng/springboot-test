package cn.com.nanfeng.redistest;

import cn.com.nanfeng.redistest.model.po.Book;
import cn.com.nanfeng.redistest.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RedisTestApplicationTests {

    @Resource
    private RedisService redisService;


    @Test
    public void testGetValue(){
        String value = redisService.getRedisKey("k1");
        log.info("value:[{}]",value);
    }

    @Test
    public void testAddValue(){
        redisService.addRedisValue("六","套");
        log.info("插入成功");
    }

    @Test
    public void testSetGetBook(){
        Book book = redisService.getBookById("2");
        log.info("book[{}]",book.toString());
    }

}
