package cn.com.nanfeng.elasticsearchtest.es.indices;

import cn.com.nanfeng.elasticsearchtest.es.util.ESClientUnils;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.client.IndicesAdminClient;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-12-23 20:48
 */
public class TestDeleteIndex {

    public static void main(String[] args) {
        IndicesAdminClient adminClient = ESClientUnils.getAdminClient();

        DeleteIndexRequestBuilder builder = adminClient.prepareDelete("test_java");

        DeleteIndexResponse response = builder.get();

        System.out.println(response.isAcknowledged());
    }
}
