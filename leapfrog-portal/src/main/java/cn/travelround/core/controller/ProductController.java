package cn.travelround.core.controller;

import cn.travelround.core.bean.product.Product;
import cn.travelround.core.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Created by Robin 2019/3/19 15:05
 */
@Controller
public class ProductController {

    @RequestMapping(value = "/")
    public String index(){
        return "index";
    }

    //search
    @Autowired
    private SearchService searchService;

    @RequestMapping(value = "/search")
    public String search(String keyword, Model model) throws Exception{
        List<Product> products = searchService.selectProductListByQuery(keyword);
        model.addAttribute("products", products);
        return "search";
    }

}
