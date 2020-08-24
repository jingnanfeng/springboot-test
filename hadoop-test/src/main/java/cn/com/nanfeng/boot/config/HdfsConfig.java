package cn.com.nanfeng.boot.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * HDFS的配置类
 * @author liutao
 * @date 2020-08-21 15:12
 */
@Configuration
@Data
public class HdfsConfig {

    @Value("${hdfs.path}")
    private String path;
}
