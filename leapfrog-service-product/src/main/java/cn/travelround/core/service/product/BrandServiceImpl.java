package cn.travelround.core.service.product;

import cn.itcast.common.page.Pagination;
import cn.travelround.core.bean.product.Brand;
import cn.travelround.core.bean.product.BrandQuery;
import cn.travelround.core.dao.product.BrandDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jiyuan on 2019/3/11.
 */

@Service("brandService")
@Transactional
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandDao brandDao;

    @Override
    public Pagination selectPaginationByQuery(String name, Integer isDisplay, Integer pageNo) {

        BrandQuery brandQuery = new BrandQuery();

        // System.out.println("bbb:" + Pagination.cpn(pageNo));

        brandQuery.setPageNo(Pagination.cpn(pageNo));
        brandQuery.setPageSize(3);
        StringBuilder params = new StringBuilder();

        if(name != null){
            brandQuery.setName(name);
            params.append("name=").append(name);
        }
        if(isDisplay != null){
            brandQuery.setIsDisplay(isDisplay);
            params.append("&isDisplay=").append(isDisplay);
        } else {
            brandQuery.setIsDisplay(1);
            params.append("&isDisplay=").append(1);
        }

        Pagination pagination = new Pagination (
                brandQuery.getPageNo(),
                brandQuery.getPageSize(),
                brandDao.selectCount(brandQuery)
        );
        pagination.setList(brandDao.selectBrandListByQuery(brandQuery));
        pagination.pageView("/brand/list.do",params.toString());

        return pagination;
    }

    @Override
    public Brand selectBrandById(Long id) {

//        return brandDao.selectBrandById(id);

        Brand brand = brandDao.selectBrandById(id);
        System.out.println("from Dao: " + brand.getImgUrl());
        System.out.println("from name: " + brand.getName());

        return brand;

    }

    @Override
    public void updateBrandId(Brand brand) {
        brandDao.updateBrandId(brand);
    }

    @Override
    public void deletes(long[] ids) {
        brandDao.deletes(ids);
    }

    @Override
    public List<Brand> selectBrandListByQuery(Integer isDisplay) {

        BrandQuery brandQuery = new BrandQuery();
        brandQuery.setIsDisplay(isDisplay);

        return brandDao.selectBrandListByQuery(brandQuery);
    }
}
