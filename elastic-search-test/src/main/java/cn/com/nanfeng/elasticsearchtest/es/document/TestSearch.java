package cn.com.nanfeng.elasticsearchtest.es.document;

import cn.com.nanfeng.elasticsearchtest.es.util.ESClientUnils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-12-24 20:25
 */
public class TestSearch {
    public static void main(String[] args) {
        TransportClient transportClient = ESClientUnils.getTransportClient();

        SearchRequestBuilder builder = transportClient.prepareSearch("test_java");

        //搜索参数的定义，如果是搜索全部数据，不传任何的参数

        SearchResponse response = builder.get();

        System.out.println("took:"+response.getTook());
        System.out.println("shart_took:"+response.getTotalShards());


    }
}
