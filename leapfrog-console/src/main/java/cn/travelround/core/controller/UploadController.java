package cn.travelround.core.controller;

import cn.travelround.common.web.Constants;
import cn.travelround.core.service.product.UploadService;
//import org.apache.camel.util.json.JsonObject;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Created by Robin 2019/3/12 11:30
 */
@Controller
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @RequestMapping(value = "/upload/uploadPic.do")
    public void uploadPic(@RequestParam(required = false)MultipartFile pic, HttpServletResponse response) throws IOException {
//        //test upload
//        System.out.println(pic.getOriginalFilename());

        String path = uploadService.uploadPic(pic.getBytes(), pic.getOriginalFilename(), pic.getSize());

        System.out.println("图片路径：" + path);
        String url = Constants.IMG_URL + path;
        System.out.println("网络路径：" + url);

        //构建json格式数据，并回传jsp
        //选择json包下的
//        JsonObject jo = new JsonObject();
        JSONObject jo = new JSONObject();
        jo.put("url", url);

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(jo.toString());

    }


    @RequestMapping(value = "/upload/uploadPics.do")
    @ResponseBody
    public List<String> uploadPics(@RequestParam(required = false)MultipartFile[] pics) throws IOException{

        List<String> urls = new ArrayList<>();

        for (MultipartFile pic : pics) {
            String path = uploadService.uploadPic(pic.getBytes(), pic.getOriginalFilename(), pic.getSize());
            String url = Constants.IMG_URL + path;
            urls.add(url);
            System.out.println("网络路径：" + url);
        }
        return urls;
    }

    @RequestMapping(value = "/upload/uploadFck.do")
    public void uploadPics(HttpServletRequest request, HttpServletResponse response) throws IOException{
        //以下操作为了获取上两个方法中的pic参数
        MultipartRequest mr = (MultipartRequest) request;
        Map<String, MultipartFile> fileMap = mr.getFileMap();

        Set<Map.Entry<String, MultipartFile>> entrySet = fileMap.entrySet();
        for (Map.Entry<String, MultipartFile> entry : entrySet) {
            //get pic
            MultipartFile pic = entry.getValue();

            //以下操作与upload单张图片相同
            String path = uploadService.uploadPic(pic.getBytes(), pic.getOriginalFilename(), pic.getSize());

            // 拼接网络路径
            String url = Constants.IMG_URL + path;
            // System.out.println("图片网络路径:" + url);
            JSONObject jo = new JSONObject();
            jo.put("error", 0);// 没有错误
            jo.put("url", url);

            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(jo.toString());

        }

    }















}
