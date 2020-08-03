package cn.com.nanfneg.redislock.runner;

import cn.com.nanfeng.commit.common.CommonStatic;
import lombok.extern.slf4j.Slf4j;
import cn.com.nanfneg.redislock.mapper.BookMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liutao
 * @title RedisRunner
 * @description 用于在项目启动时加载需要的redis相关内容
 * @date 2020-08-01 23:06
 */
@Slf4j
@Component
public class RedisRunner implements CommandLineRunner{

    @Resource
    private RedisBloomService redisBloomService;
    @Resource
    private BookMapper bookMapper;
    @Resource
    private BloomFilterHelper<String> bloomFilterHelper;


    @Override
    public void run(String... args) throws Exception {
        log.info(">>>>>>>>>>>>RedisRunner<<<<<<<<<<<<<<<<<<");
        List<String> bookNameList = bookMapper.selectBookName();
        //初始化布隆过滤器
        for (String bookName : bookNameList) {
            redisBloomService.addByBloomFilter(bloomFilterHelper, CommonStatic.BOOK_NAME,bookName);
        }
    }
}
