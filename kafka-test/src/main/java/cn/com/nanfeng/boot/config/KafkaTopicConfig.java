package cn.com.nanfeng.boot.config;

import cn.com.nanfeng.boot.constants.GlobalConstant;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 使用代码创建的topic
 * 三个参数意思：topic的名称；分区数量，新主题的复制因子；
 * 如果指定了副本分配，则为-1
 * @author liutao
 * @date 2020-08-18 13:55
 */
@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic batchTopic(){
        return new NewTopic(GlobalConstant.TEST_TOPIC,8,(short) 1);
    }

}
