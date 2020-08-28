package cn.com.nanfeng.boot.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * 反序列化类
 * @author liutao
 * @date 2020-08-28 9:34
 */
public class JsonDeserializer<T> {

    private final Class<T> recordClazz;
    private final ObjectMapper jsonMapper;

    public JsonDeserializer(Class<T> recordClazz){
        this.recordClazz = recordClazz;
        this.jsonMapper = new ObjectMapper();
    }

    public T parseFromString(String line){
        try {
            return jsonMapper.readValue(line,this.recordClazz);
        }catch (IOException e){
            throw new IllegalArgumentException("Cloud not deserialize record: " + line + "as class" + recordClazz);
        }
    }
}
