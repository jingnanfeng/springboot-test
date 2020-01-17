package cn.com.nanfeng.elasticsearchtest.es.document;

import cn.com.nanfeng.elasticsearchtest.es.util.ESClientUnils;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-12-23 21:01
 */
public class TestCreateDocument {
    public static void main(String[] args) {
        TransportClient transportClient = ESClientUnils.getTransportClient();

        IndexRequestBuilder builder = transportClient.prepareIndex("test_java","java_type","1");
        /**
         * 通过字符串新增
         */
        /*String docStr = "{\"name\":\"zhangsan\",\"tags\":\"tags1\",\"remark\":\"this is remark\"}";
        //设置请求参数，要新增的数据对象
        builder.setSource(docStr, XContentType.JSON);*/
        /**
         * 通过对象新增
         */
        try{
            builder.setSource(
                    XContentFactory.jsonBuilder()
                            .startObject()
                            .field("name","lisi")
                            .field("tags","tags2")
                            .field("remark","this is remark")
                            .endObject()
            );
        }catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }


        IndexResponse response = builder.get();

        System.out.println(response.getIndex());
        System.out.println(response.getType());
        System.out.println(response.getId());
        System.out.println(response.getVersion());
        System.out.println(response.status());
        System.out.println(response.getShardInfo().getTotal());

        //回收资源
        transportClient.close();
    }
}
