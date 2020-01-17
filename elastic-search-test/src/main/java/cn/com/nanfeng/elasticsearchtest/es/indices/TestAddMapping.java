package cn.com.nanfeng.elasticsearchtest.es.indices;

import cn.com.nanfeng.elasticsearchtest.es.util.ESClientUnils;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequestBuilder;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.common.xcontent.XContentType;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-12-23 20:40
 */
public class TestAddMapping {
    public static void main(String[] args) {
        IndicesAdminClient adminClient = ESClientUnils.getAdminClient();
        //创建mapping
        PutMappingRequestBuilder builder = adminClient.preparePutMapping("test_java");
        //设置新增映射的类型是什么
        builder.setType("java_type");
        //设置新增映射的内容
        builder.setSource("{\"properties\":{\"remark\":{\"type\":\"text\"}}}", XContentType.JSON);

        PutMappingResponse response = builder.get();

        System.out.println(response.isAcknowledged());
    }
}
