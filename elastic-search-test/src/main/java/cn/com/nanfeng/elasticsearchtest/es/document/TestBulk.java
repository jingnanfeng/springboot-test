package cn.com.nanfeng.elasticsearchtest.es.document;

import cn.com.nanfeng.elasticsearchtest.es.util.ESClientUnils;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentFactory;

import java.io.IOException;
import java.util.Iterator;

/**
 * @author liutao
 * @Title 批量的写操作
 * @Description
 * @date 2019-12-23 21:42
 */
public class TestBulk {

    public static void main(String[] args) throws IOException {

        TransportClient transportClient = ESClientUnils.getTransportClient();

        BulkRequestBuilder bulkRequestBuilder = transportClient.prepareBulk();
        //bulk就是多个写操作，传递不同的请求构建器，到add方法，就是批量写操作

        bulkRequestBuilder.add(
                transportClient.prepareIndex("test_java","java_type","1")
                .setSource(
                        XContentFactory.jsonBuilder()
                        .startObject()
                            .field("name","张三")
                            .field("tags","tags10")
                            .field("remark","test remark")
                        .endObject()
                )
        );
        //依次调用add方法

        //批量写操作的返回结果集合，可以迭代
        BulkResponse responses = bulkRequestBuilder.get();

        //判断批量写操作中是否有单元错误
        boolean b = responses.hasFailures();
        System.out.println(b);

        for (BulkItemResponse respons : responses) {
            DocWriteResponse response = respons.getResponse();

            System.out.println(response.getShardInfo());
        }
    }

}
