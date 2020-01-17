package cn.com.nanfeng.elasticsearchtest.es.document;

import cn.com.nanfeng.elasticsearchtest.es.util.ESClientUnils;
import org.elasticsearch.action.delete.DeleteRequestBuilder;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.client.transport.TransportClient;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-12-23 21:26
 */
public class TestDeleteDoc {

    public static void main(String[] args) {
        TransportClient transportClient = ESClientUnils.getTransportClient();
        DeleteRequestBuilder builder = transportClient.prepareDelete("test_java", "java_type", "1");

        DeleteResponse response = builder.get();




    }
}
