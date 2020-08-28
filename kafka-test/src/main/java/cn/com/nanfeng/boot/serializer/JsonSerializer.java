package cn.com.nanfeng.boot.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 序列化类
 * @author liutao
 * @date 2020-08-28 9:26
 */
public class JsonSerializer<T> {

    private final ObjectMapper jsonMapper = new ObjectMapper();

    /**
     * 转换成json字符串
     * @param r
     * @return
     */
    public String toJSONString(T r){
        try {
            return jsonMapper.writeValueAsString(r);
        }catch (JsonProcessingException e){
            throw new IllegalArgumentException("Cloud not serialize recode: " + r,e);
        }
    }

    /**
     * 转成成字节数组
     * @param r
     * @return
     */
    public byte[] toJSONBytes(T r){
        try {
            return jsonMapper.writeValueAsBytes(r);
        }catch (JsonProcessingException e){
            throw new IllegalArgumentException("cloud not serialize recode:" + r,e);
        }
    }


}
