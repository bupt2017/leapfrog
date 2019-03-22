package cn.travelround.core.service;

import cn.travelround.core.bean.product.Product;

import java.util.List;

/**
 * @author Created by Robin 2019/3/20 10:30
 */
public interface SearchService {
    List<Product> selectProductListByQuery(String keyword) throws Exception;
}
