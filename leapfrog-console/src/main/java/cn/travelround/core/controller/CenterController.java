package cn.travelround.core.controller;

import cn.travelround.core.bean.TestTb;
import cn.travelround.core.service.TestTbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/control")
public class CenterController {

  /*  @Autowired
    private TestTbService testTbService;*/

    @RequestMapping(value = "/index.do")
    public String index(Model model){

        /*TestTb testTb = new TestTb();
        testTb.setName("jiyuan");
        testTbService.insertTestTb(testTb);*/

        return "index";
    }

    @RequestMapping(value = "/top.do")
    public String top(){
        return "top";
    }
    @RequestMapping(value = "/main.do")
    public String main(){
        return "main";
    }
    @RequestMapping(value = "/left.do")
    public String left(){
        return "left";
    }
    @RequestMapping(value = "/right.do")
    public String right(){
        return "right";
    }
    @RequestMapping(value = "frame/product_main.do")
    public String product_main(){
        return "frame/product_main";
    }
    @RequestMapping(value = "frame/product_left.do")
    public String product_left(){
        return "frame/product_left";
    }
}
