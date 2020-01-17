package cn.com.nanfeng.elasticsearchtest.es.document;

import cn.com.nanfeng.elasticsearchtest.es.util.ESClientUnils;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-12-23 21:29
 */
public class TestGet {

    public static void main(String[] args) {
        TransportClient transportClient = ESClientUnils.getTransportClient();

        GetResponse response = transportClient.prepareGet("test_java", "java_type", "1").get();

        System.out.println(response.getIndex());
        System.out.println(response.getType());
        System.out.println(response.getId());
        System.out.println(response.getVersion());
        System.out.println(response.getSource());
        System.out.println(response.getSourceAsString());
    }
}
