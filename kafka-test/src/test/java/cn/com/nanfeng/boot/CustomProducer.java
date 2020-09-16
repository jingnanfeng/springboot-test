package cn.com.nanfeng.boot;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * @author liutao
 * @date 2020-09-04 15:24
 */
public class CustomProducer {

    public static void main(String[] args) throws ExecutionException,InterruptedException {
        Properties properties = new Properties();
        //设置kafka集群
        properties.put("bootstrap.servers","hadoop1:9092");
        properties.put("acks","all");
        //重试次数
        properties.put("retries",1);
        //批次大小
        properties.put("batch.size",1024 * 16);
        //等待时间
        properties.put("linger.ms",1);
        //缓冲区大小
        properties.put("buffer.memory",33554432);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");

        //构建拦截器
        List<String> interceptorList = new ArrayList<>();
        interceptorList.add("cn.com.nanfeng.boot.TimeInterceptor");
        interceptorList.add("cn.com.nanfeng.boot.CounterInterceptor");
        properties.put(ProducerConfig.INTERCEPTOR_CLASSES_DOC,interceptorList);

        Producer<String,String> producer = new KafkaProducer<>(properties);
        for (int i = 0; i < 100; i++) {
            producer.send(new ProducerRecord<>("testTopic", Integer.toString(i), Integer.toString(i)), new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception e) {
                    //回调函数，该方法会在producer收到ack时回调，为异步调用
                    if (e == null){
                        System.out.println("success->"+metadata.offset());
                    }else {
                        e.printStackTrace();
                    }
                }
            });
        }
        producer.close();

    }

}
