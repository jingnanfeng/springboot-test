package cn.com.nanfeng.elasticsearchtest.es.indices;

import cn.com.nanfeng.elasticsearchtest.es.util.ESClientUnils;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;

/**
 * @author liutao
 * @Title 创建索引test
 * @Description
 * @date 2019-12-22 14:44
 */
public class TestCreateIndex {

    public static void main(String[] args) {
        IndicesAdminClient adminClient = ESClientUnils.getAdminClient();
        //创建索引请求构建体
        CreateIndexRequestBuilder builder = adminClient.prepareCreate("test_java");
        //创建索引的参数设置
        builder.setSettings(
          Settings.builder().put("number_of_shards","3").put("number_of_replicas","1").build()
        );
        //定义映射mapping
        //builder.addMapping("{\"java_type\":{\"properties\":{\"name\":{\"type\":\"text\",\"analyzer\":\"ik_max_word\"},\"tags\":{\"type\":\"text\"}}}}", XContentType.JSON);
        builder.addMapping("java_type","name","type=text,analyzer=ik_max_word",
                "tags","type=text");

        //发请求并等待相应
        CreateIndexResponse response = builder.get();

        System.out.println(response.isAcknowledged());
        System.out.println(response.isAcknowledged());
        System.out.println(response.index());




    }
}
