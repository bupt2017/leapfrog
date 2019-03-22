package cn.travelround;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Created by Robin 2019/3/20 9:07
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"classpath:application-context.xml"})
public class TestSolr {

    @Autowired
    private SolrServer solrServer;

    @Test
    public void testSolr() throws Exception{
//        String baseUrl = "http://192.168.177.129:8080/solr";
//        SolrServer solrServer = new HttpSolrServer(baseUrl);

        SolrInputDocument doc = new SolrInputDocument();
//        doc.setField("id", 33);
//        doc.setField("name", "姬汪儿");
        doc.setField("id", 34);
        doc.setField("name", "姬汪汪");
        solrServer.add(doc);
        solrServer.commit();
    }
}


