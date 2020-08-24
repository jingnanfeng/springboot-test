package cn.com.nanfeng.boot;

import cn.com.nanfeng.boot.service.HdfsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.fs.FileSystem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author liutao
 * @date 2020-08-21 15:58
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class HdfsTests {

    @Resource
    private HdfsService hdfsService;

    @Test
    public void testGetFileSystem() throws Exception{
        FileSystem fileSystem = hdfsService.getFileSystem();
        log.info(fileSystem.toString());
    }

    @Test
    public void testReadFile() throws Exception{
        String context = hdfsService.readFile("/user/nanfeng/input/wc.input");
        log.info(context);
    }

}
