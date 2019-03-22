package cn.travelround.core.service.product;

import cn.itcast.common.page.Pagination;
import cn.travelround.core.bean.product.Color;
import cn.travelround.core.bean.product.Product;

import java.util.List;

/**
 * @author Created by Robin 2019/3/18 9:36
 */
public interface ProductService {

    Pagination selectPaginationByQuery(Integer pageNo, String name, Long brandId, Boolean isShow);

    List<Color> selectColorList();

    void insertProduct(Product product);

    void isShow(Long[] ids);
}
