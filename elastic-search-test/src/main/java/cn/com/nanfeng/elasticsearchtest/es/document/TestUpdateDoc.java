package cn.com.nanfeng.elasticsearchtest.es.document;

import cn.com.nanfeng.elasticsearchtest.es.util.ESClientUnils;
import org.elasticsearch.action.update.UpdateRequestBuilder;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentFactory;

import java.io.IOException;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-12-23 21:20
 */
public class TestUpdateDoc {

    public static void main(String[] args) {
        TransportClient transportClient = ESClientUnils.getTransportClient();
        //部分更新
        UpdateRequestBuilder builder = transportClient.prepareUpdate("test_java", "java_type", "1");
        try {
            builder.setDoc(
                    XContentFactory.jsonBuilder()
                            .startObject()
                            .field("name","wangwu")
                            .endObject()
            );
        }catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        UpdateResponse updateResponse = builder.get();


    }
}
