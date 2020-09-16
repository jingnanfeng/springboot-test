package cn.com.nanfeng.boot.controller;

import cn.com.nanfeng.boot.constants.GlobalConstant;
import cn.com.nanfeng.boot.model.SingleMessage;
import com.alibaba.fastjson.JSONObject;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * kafka生产者
 * @author liutao
 * @date 2020-08-18 10:51
 */
@RestController
public class ProducerController {


    @Resource
    private KafkaTemplate<String,Object> kafkaTemplate;

    /**
     * 指定在1分区发送内容
     * @param msg
     * @return
     */
    @GetMapping("/message/send")
    public String send(){
        //使用kafka发送消息
        long timeMillis = System.currentTimeMillis();
        SingleMessage singleMessage = new SingleMessage();
        singleMessage.setTimeLong(timeMillis);
        singleMessage.setName("zhangsan");
        String json = JSONObject.toJSONString(singleMessage);
        kafkaTemplate.send(GlobalConstant.TOPIC,"1",json);
        return "success";
    }


}
