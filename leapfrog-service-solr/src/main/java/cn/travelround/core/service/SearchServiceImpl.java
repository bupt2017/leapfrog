package cn.travelround.core.service;

import cn.travelround.core.bean.product.Product;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Created by Robin 2019/3/20 10:34
 */
@Service(value = "searchService")
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SolrServer solrServer;

    @Override
    public List<Product> selectProductListByQuery(String keyword) throws Exception{

        //此方法要做的事情
        //keyword
        //过滤条件
        //高亮
        //排序
        //分页

        List<Product> products = new ArrayList<>();
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.set("q", "name_ik:" + keyword);

        QueryResponse response = solrServer.query(solrQuery);
        SolrDocumentList docs = response.getResults(); //查到的结果集
        long numFound = docs.getNumFound();
        for (SolrDocument doc : docs) {
            Product product = new Product();

            // product id
            String id = (String) doc.get("id");
            product.setId(Long.parseLong(id));

            // product name
            String name = (String) doc.get("name_ik");
            product.setName(name);

            // picture
            String url = (String) doc.get("url");
            product.setImgUrl(url);

            // price
            //需要在Product中添加price字段
            Float price = (Float) doc.get("price");
            product.setPrice(price);

            //brand id
            Integer brandId = (Integer) doc.get("brandId");
            product.setBrandId(Long.parseLong(String.valueOf(brandId)));

            products.add(product);
        }

        return products;
    }
}
