package cn.com.nanfeng.elasticsearchtest.es.indices;

import cn.com.nanfeng.elasticsearchtest.es.util.ESClientUnils;
import org.elasticsearch.action.admin.indices.settings.put.UpdateSettingsRequestBuilder;
import org.elasticsearch.action.admin.indices.settings.put.UpdateSettingsResponse;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.common.settings.Settings;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-12-22 15:39
 */
public class TestUpdateIndexSettings {

    public static void main(String[] args) {

        IndicesAdminClient adminClient = ESClientUnils.getAdminClient();
        //更新索引的构建器
        UpdateSettingsRequestBuilder builder = adminClient.prepareUpdateSettings("test_java");
        builder.setSettings(
                Settings.builder().put("number_of_replicas",2).build()
        );
        UpdateSettingsResponse response = builder.get();
        System.out.println(response.isAcknowledged());
    }

}
