package cn.com.nanfeng.boot.consumer;

import cn.com.nanfeng.boot.constants.GlobalConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author liutao
 * @title ConsumerDemo
 * @description
 * @date 2020-08-18 10:56
 */
@Configuration
@Slf4j
public class ConsumerDemo {



    /**
     * 配置topic和分区，可以配置多个
     * topic为队列名称
     * partitions表示值的分区，这里制定了0和2分区
     * partitionOffser表示消息的指定分区，partition表示那个分区，
     * initialOffset表示Offset的初始位置
     * @param consumerRecord
     */
//   @KafkaListener(topicPartitions = {
//           @TopicPartition(topic = GlobalConstant.TEST_TOPIC,
//                   partitions = {"0","2"},
//                   partitionOffsets = @PartitionOffset(partition = "1",initialOffset = "4"))
//   })
    @KafkaListener(topics = GlobalConstant.FTARGET)
    public void consumer(ConsumerRecord consumerRecord){
       Optional<Object> kafkaMessage = Optional.ofNullable(consumerRecord.value());
       if (kafkaMessage.isPresent()){
           Object o = kafkaMessage.get();
           log.info("接收的消息是：[{}]",o);
       }
   }
}
