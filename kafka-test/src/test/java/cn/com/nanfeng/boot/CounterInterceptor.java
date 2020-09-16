package cn.com.nanfeng.boot;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * @author liutao
 * @date 2020-09-04 16:37
 */
public class CounterInterceptor implements ProducerInterceptor<String,String> {

    private int errorCounter = 0;
    private int seuuceeCount = 0;


    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> producerRecord) {
        return null;
    }

    @Override
    public void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {
        //统计成功和失败的次数
        if (e == null){
            seuuceeCount++;
        }else {
            errorCounter++;
        }

    }

    @Override
    public void close() {
        //保存结果
        System.out.println("Successful sent" + seuuceeCount);
        System.out.println("Faild sent" + errorCounter);
    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
