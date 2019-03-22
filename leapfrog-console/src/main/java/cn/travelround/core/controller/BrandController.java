package cn.travelround.core.controller;

import cn.itcast.common.page.Pagination;
import cn.travelround.core.bean.product.Brand;
import cn.travelround.core.service.product.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jiyuan on 2019/3/11.
 */
@Controller
@RequestMapping(value = "/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @RequestMapping(value = "/list.do")
    public String list(String name,Integer isDisplay,Integer pageNo,Model model){

//        System.out.println("aaa:"+pageNo);

        Pagination pagination = brandService.selectPaginationByQuery(name,isDisplay,pageNo);
        model.addAttribute("pagination",pagination);
        model.addAttribute("name",name);
        if(null != isDisplay){
            model.addAttribute("isDisplay",isDisplay);
        } else {
            model.addAttribute("isDisplay",1);
        }

        return "brand/list";
    }

    //去修改页面
    @RequestMapping(value = "/toEdit.do")
    public String list(Long id, Model model){

        Brand brand = brandService.selectBrandById(id);

        model.addAttribute("brand", brand);
        System.out.println("brand的值是：" + brand.getName());
        System.out.println("pic是：" + brand.getImgUrl());
        System.out.println("isDisplay； " + brand.getIsDisplay());
        return "brand/edit";
    }

    //修改
    @RequestMapping(value = "/edit.do")
    public String edit(Brand brand){
        brandService.updateBrandId(brand);
        //提交修改后需要更新list页的显示数据，所以重定向到查询方法重新查询数据
        return "redirect:/brand/list.do";
    }

    //删除
    @RequestMapping(value = "/deletes.do")
    public String edit(long[] ids){
        brandService.deletes(ids);
        return "forward:/brand/list.do";
    }

}
