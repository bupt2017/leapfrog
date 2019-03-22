package cn.travelround.core.controller;

import cn.itcast.common.page.Pagination;
import cn.travelround.core.bean.product.Brand;
import cn.travelround.core.bean.product.Color;
import cn.travelround.core.bean.product.Product;
import cn.travelround.core.service.product.BrandService;
import cn.travelround.core.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Created by Robin 2019/3/18 9:12
 */
@Controller
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private BrandService brandService;

    @RequestMapping(value = "/product/list.do")
    public String list(Integer pageNo, String name, Long brandId, Boolean isShow, Model model){

        //品牌 - 点击下拉时，出现的品牌
        List<Brand> brands = brandService.selectBrandListByQuery(1);
        model.addAttribute("brands", brands);

        //获取页面显示数据（分页）
        Pagination pagination = productService.selectPaginationByQuery(pageNo, name, brandId, isShow);
        model.addAttribute("pagination", pagination);
        model.addAttribute("name", name);
        model.addAttribute("brandId", brandId);

        if(isShow != null){
            model.addAttribute("isShow", isShow);
        }else{
            model.addAttribute("isShow", false);
        }

        return "product/list";
    }

    @RequestMapping(value = "/product/toAdd.do")
    public String toAdd(Model model){
        //品牌 - 点击下拉时，出现的品牌
        List<Brand> brands = brandService.selectBrandListByQuery(1);
        model.addAttribute("brands", brands);

        List<Color> colors = productService.selectColorList();
        model.addAttribute("colors", colors);

        return "product/add";
    }

    @RequestMapping(value = "/product/add.do")
    public String toAdd(Product product){
        productService.insertProduct(product);
        return "redirect:/product/list.do";
    }

    //批量 上架
    @RequestMapping(value = "/product/isShow.do")
    public String toAdd(Long[] ids){
        productService.isShow(ids);
        return "forward:/product/list.do";
    }


}
