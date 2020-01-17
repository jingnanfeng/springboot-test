package cn.com.nanfeng.elasticsearchtest.es.document;

import cn.com.nanfeng.elasticsearchtest.es.util.ESClientUnils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;

import java.util.List;
import java.util.Map;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-12-24 20:37
 */
public class TestSearchQuery {

    public static void main(String[] args) {

        TransportClient transportClient = ESClientUnils.getTransportClient();

        SearchRequestBuilder builder = transportClient.prepareSearch("test_index");

        /**
         * 设置搜索条件setQuery(QueryBuilder)
         * ES提供工具类 QueryBuilder,工具类辅助创建QueryBuilder对象
         * 工具类中定义若干个方法，每一个静态方法都对应搜索参数上的一个key
         */
        //builder.setQuery(QueryBuilders.matchAllQuery());

      /*  builder.setQuery(QueryBuilders.matchQuery("name","李"));
        //range范围 大于20，小于30
        builder.setQuery(QueryBuilders.rangeQuery("age").gte(20).lte(30));*/

        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        List<QueryBuilder> mustList = queryBuilder.must();
        mustList.add(QueryBuilders.matchQuery("name","wang"));
        mustList.add(QueryBuilders.rangeQuery("age").gte(20).lte(30));
        List<QueryBuilder> shouldList = queryBuilder.should();
        shouldList.add(QueryBuilders.matchQuery("name","li"));

        builder.setQuery(queryBuilder);
        //排序
        //builder.addSort(SortBuilders.fieldSort("age").order(SortOrder.ASC));

        int page = 1;
        int rows = 2;
        builder.setFrom((page-1)*rows);
        builder.setSize(rows);
        //高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        HighlightBuilder.Field field = new HighlightBuilder.Field("name");
        field.fragmentSize(10);
        field.numOfFragments(1);
        highlightBuilder.field(field);
        highlightBuilder.preTags("<span style = 'color:red'>");
        highlightBuilder.postTags("</span>");

        SearchResponse response = builder.get();
        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println(hit.getSourceAsString());
            Map<String, HighlightField> map = hit.getHighlightFields();
            System.out.println(map);
            for (Map.Entry<String, HighlightField> entry : map.entrySet()) {
                System.out.println("hit key:"+entry.getKey());
                System.out.println("hit value:"+entry.getValue());
            }
        }


    }

}
