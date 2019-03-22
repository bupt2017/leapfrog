package cn.travelround.core.dao.product;

import cn.travelround.core.bean.product.Brand;
import cn.travelround.core.bean.product.BrandQuery;

import java.util.List;

/**
 * Created by jiyuan on 2019/3/11.
 */
public interface BrandDao {
    Integer selectCount(BrandQuery brandQuery);

    List<Brand> selectBrandListByQuery(BrandQuery brandQuery);

    Brand selectBrandById(Long id);

    void updateBrandId(Brand brand);

    void deletes(long[] ids);
}
