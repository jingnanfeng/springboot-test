package cn.com.nanfeng.elasticsearchtest.es.document;

import cn.com.nanfeng.elasticsearchtest.es.util.ESClientUnils;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetRequestBuilder;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.client.transport.TransportClient;

/**
 * @author liutao
 * @Title 批量查询
 * @Description
 * @date 2019-12-23 21:39
 */
public class TestMGET {

    public static void main(String[] args) {
        TransportClient transportClient = ESClientUnils.getTransportClient();

        MultiGetRequestBuilder builder = transportClient.prepareMultiGet();

        builder.add("test_java","java_type","1");

        MultiGetResponse responses = builder.get();

        for (MultiGetItemResponse respons : responses) {
            GetResponse response = respons.getResponse();

            System.out.println(response.getSourceAsString());
        }
    }
}
