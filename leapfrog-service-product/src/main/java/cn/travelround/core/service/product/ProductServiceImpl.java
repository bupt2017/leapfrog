package cn.travelround.core.service.product;

import cn.itcast.common.page.Pagination;
import cn.travelround.core.bean.product.*;
import cn.travelround.core.dao.product.ColorDao;
import cn.travelround.core.dao.product.ProductDao;
import cn.travelround.core.dao.product.SkuDao;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.Date;
import java.util.List;

/**
 * @author Created by Robin 2019/3/18 9:36
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ColorDao colorDao;

    @Override
    public Pagination selectPaginationByQuery(Integer pageNo, String name, Long brandId, Boolean isShow) {

        ProductQuery productQuery = new ProductQuery();
        productQuery.setPageNo(Pagination.cpn(pageNo));
        productQuery.setOrderByClause("id desc");  //排序
        ProductQuery.Criteria createCriteria = productQuery.createCriteria();

        StringBuilder params = new StringBuilder();
        if(name != null){
            createCriteria.andNameLike("%" + name + "%");
            params.append("name=").append(name);
        }
        if(brandId != null){
            createCriteria.andBrandIdEqualTo(brandId);
            params.append("&brandId=").append(brandId);
        }
        if(isShow != null){
            createCriteria.andIsShowEqualTo(isShow);
            params.append("&isShow=").append(isShow);
        }else{
            createCriteria.andIsShowEqualTo(false);
            params.append("&isShow=").append(false);
        }

        Pagination pagination = new Pagination(productQuery.getPageNo(),
                productQuery.getPageSize(),
                productDao.countByExample(productQuery),
                productDao.selectByExample(productQuery));

        return pagination;
    }

    @Override
    public List<Color> selectColorList() {

        ColorQuery colorQuery = new ColorQuery();
        colorQuery.createCriteria().andParentIdNotEqualTo(0L);  //不要父颜色
        return colorDao.selectByExample(colorQuery);
    }

    @Autowired
    private SkuDao skuDao; //sku为库存模块
    @Autowired
    private Jedis jedis;

    @Override
    public void insertProduct(Product product) {

        //使用redis生成id
        Long id = jedis.incr("pno");
        product.setId(id);

        //新添加的商品默认为下架状态
        product.setIsShow(false);
        product.setIsDel(true);   //true代表不删除
        productDao.insertSelective(product);
        System.out.println("生成的商品id" + product.getId());

        String[] colors = product.getColors().split(",");
        String[] sizes = product.getSizes().split(",");
        //一个颜色会对应多个尺码，有可能红色没有L码，但白色就有，所以分开遍历添加
        for (String color : colors) {
            for (String size : sizes) {
                Sku sku = new Sku();
                sku.setProductId(product.getId());
                sku.setColorId(Long.parseLong(color));
                sku.setSize(size);
                sku.setMarketPrice(999f);       //市场价
                sku.setPrice(666f);             //售价
                sku.setDeliveFee(8f);           //运费
                sku.setStock(0);                //库存，新增商品默认为0
                sku.setUpperLimit(200);         //购买限制
                sku.setCreateTime(new Date());  //添加时间
                skuDao.insertSelective(sku);
            }
        }
    }

    //上架
    @Autowired
    private SolrServer solrServer;

    @Override
    public void isShow(Long[] ids) {
        Product product = new Product();
        product.setIsShow(true);

        for (Long id : ids) {
            product.setId(id);
            productDao.updateByPrimaryKeySelective(product);

            //TODO 1.保存商品信息到Solr服务器
            SolrInputDocument doc = new SolrInputDocument();
            doc.setField("id", id);

            Product p = productDao.selectByPrimaryKey(id);
            doc.setField("name_ik", p.getName());
            doc.setField("url", p.getImages()[0]);

            //价格
            SkuQuery skuQuery = new SkuQuery();
            skuQuery.createCriteria().andProductIdEqualTo(id);
            skuQuery.setOrderByClause("price asc");
            skuQuery.setPageNo(1);
            skuQuery.setPageSize(1);
            skuQuery.setFields("price");
            List<Sku> skus = skuDao.selectByExample(skuQuery);

            doc.setField("price", skus.get(0).getPrice());
            doc.setField("brandId", p.getBrandId());

            try {
                solrServer.add(doc);
                solrServer.commit();
            }catch (Exception e){
                e.printStackTrace();
            }

            //TODO 2.静态化技术引入

        }
    }
}
