package cn.com.nanfeng.elasticsearchtest.es.util;

import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.util.Properties;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-12-21 23:14
 */
public class ESClientUnils {

    private static final Properties PROPERTIES = new Properties();

    private static TransportClient transportClient;

    private static IndicesAdminClient adminClient;

    static {
        /**
         *  ESClientUnils.class 获取类对象
         *  Class.getClassLoader 获取类加载器
         *  ClassLoader.getResourceAsStream 从类路径下开始寻找，并创建文件输入流
         */
        InputStream in = ESClientUnils.class.getClassLoader().getResourceAsStream("es.properties");
        try {
            PROPERTIES.load(in);
        }catch (IOException e){
            e.printStackTrace();
            //在静态代码块中唯一可以抛出的错误
            throw new ExceptionInInitializerError(e);
        }

        transportClient = new PreBuiltTransportClient(
                Settings.builder().put("cluster.name",PROPERTIES.getProperty("cluster.name")).build()
        );

        String[] ips = PROPERTIES.getProperty("cluster.ips").split(",");
        String[] ports = PROPERTIES.getProperty("cluster.ports").split(",");
        for (int i = 0; i < ips.length; i++) {
            transportClient.addTransportAddress(
                    new TransportAddress(
                            new InetSocketAddress(ips[i],Integer.parseInt(ports[i]))
                )
            );
        }

        adminClient = transportClient.admin().indices();

    }

    public static TransportClient getTransportClient(){
        return transportClient;
    }

    public static IndicesAdminClient getAdminClient(){
        return  adminClient;
    }

}
