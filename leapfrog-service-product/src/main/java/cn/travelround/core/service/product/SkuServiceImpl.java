package cn.travelround.core.service.product;

import cn.travelround.core.bean.product.Sku;
import cn.travelround.core.bean.product.SkuQuery;
import cn.travelround.core.dao.product.ColorDao;
import cn.travelround.core.dao.product.SkuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Created by Robin 2019/3/19 10:23
 */
@Service(value = "skuService")
public class SkuServiceImpl implements SkuService {

    @Autowired
    private SkuDao skuDao;
    @Autowired
    private ColorDao colorDao;

    //根据商品id，查询库存结果
    @Override
    public List<Sku> selectSkuListByProductId(Long productId) {

        SkuQuery skuQuery = new SkuQuery();
        skuQuery.createCriteria().andProductIdEqualTo(productId);
        List<Sku> skus = skuDao.selectByExample(skuQuery);
        for (Sku sku : skus) {
            sku.setColor(colorDao.selectByPrimaryKey(sku.getColorId()));

        }
        return skus;
    }

    @Override
    public void updateSkuById(Sku sku) {
        skuDao.updateByPrimaryKeySelective(sku);
    }
}
