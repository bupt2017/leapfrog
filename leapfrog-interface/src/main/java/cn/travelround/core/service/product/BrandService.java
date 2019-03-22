package cn.travelround.core.service.product;

import cn.itcast.common.page.Pagination;
import cn.travelround.core.bean.product.Brand;

import java.util.List;

/**
 * Created by jiyuan on 2019/3/11.
 */
public interface BrandService {
    public Pagination selectPaginationByQuery(String name,Integer isDisplay,Integer pageNo);

    Brand selectBrandById(Long id);

    void updateBrandId(Brand brand);

    void deletes(long[] ids);

    List<Brand> selectBrandListByQuery(Integer isDisplay);
}
