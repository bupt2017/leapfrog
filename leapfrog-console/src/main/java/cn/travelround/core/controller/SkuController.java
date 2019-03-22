package cn.travelround.core.controller;

import cn.travelround.core.bean.product.Sku;
import cn.travelround.core.service.product.SkuService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Created by Robin 2019/3/19 10:14
 */
@Controller
public class SkuController {

    @Autowired
    private SkuService skuService;

    @RequestMapping(value = "/sku/list.do")
    public String list(Long productId, Model model){

        List<Sku> skus = skuService.selectSkuListByProductId(productId);
        model.addAttribute("skus", skus);
        return "sku/list";
    }

    @RequestMapping(value = "/sku/addSku.do")
    public void list(Sku sku, HttpServletResponse response) throws IOException {

        skuService.updateSkuById(sku);
        //选择org.json包
        JSONObject jo = new JSONObject();
        jo.put("message", "update success");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(jo.toString());

    }

}
