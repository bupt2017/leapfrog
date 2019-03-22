package cn.travelround.core.service.product;

import cn.travelround.core.bean.product.Sku;

import java.util.List;

/**
 * @author Created by Robin 2019/3/19 10:22
 */
public interface SkuService {

    List<Sku> selectSkuListByProductId(Long productId);

    void updateSkuById(Sku sku);
}
