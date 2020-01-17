package cn.com.nanfeng.elasticsearchtest.es;

import cn.com.nanfeng.elasticsearchtest.es.util.ESClientUnils;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetSocketAddress;

/**
 * @author liutao
 * @Title 如何连接es服务器
 * @Description
 * @date 2019-12-21 22:23
 */
public class FirstESClientTest {

    /*public static void main(String[] args) {
        //创建配置对象
        *//**
         * 获取一个构建器对象
         * builder.put("key","value") 设置配置参数，必须设置的集群名称
         * 通过工厂创建settings对象
         *//*
        Settings settings = Settings.builder()
                .put("cluster.name","elasticsearch")
                .build();
        //创建客户端,是做document数据交互访问的客户端(只能做文档的增删改查)
        TransportClient transportClient = new PreBuiltTransportClient(settings);

        //创建服务器所在地址和端口的对象
        InetSocketAddress add1 =  new InetSocketAddress("192.168.147.134",9300);
        InetSocketAddress add2 =  new InetSocketAddress("192.168.147.135",9300);
        //绑定地址
        transportClient.addTransportAddress(
                new TransportAddress(add1)
        );
        transportClient.addTransportAddress(
                new TransportAddress(add2)
        );

        //通过transportClient对象，创建一个索引管理的客户端
        IndicesAdminClient adminClient = transportClient.admin().indices();

        System.out.println(transportClient);

        System.out.println(adminClient);


    }*/
    public static void main(String[] args) {
        TransportClient transportClient = ESClientUnils.getTransportClient();
        IndicesAdminClient adminClient = ESClientUnils.getAdminClient();

        System.out.println(transportClient);
        System.out.println(adminClient);
    }

}
