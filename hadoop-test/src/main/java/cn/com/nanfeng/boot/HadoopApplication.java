package cn.com.nanfeng.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hadoop和springboot整合，使用springboot去操作hdfs的文件
 * @author liutao
 * @date 2020-08-21 14:55
 */
@SpringBootApplication
public class HadoopApplication {

    public static void main(String[] args) {
        SpringApplication.run(HadoopApplication.class,args);
    }

}
