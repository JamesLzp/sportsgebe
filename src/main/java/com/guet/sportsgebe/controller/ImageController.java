package com.guet.sportsgebe.controller;

import com.alibaba.fastjson.JSON;
import com.guet.sportsgebe.entity.Image;
import com.guet.sportsgebe.service.ImageService;
import com.guet.sportsgebe.util.GetCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * (Image)表控制层
 *
 * @author makejava
 * @since 2020-04-10 02:41:56
 */
@Controller
@RequestMapping(value = "/Images")
public class ImageController {
    /**
     * 服务对象
     */
    @Resource
    private ImageService imageService;

    @Value("${web.upload-path}")
    private String path;

    @RequestMapping("/GetActImg")
    public void GetActImg(HttpServletRequest request, HttpServletResponse response){
        String ActId = (String) request.getSession().getAttribute("ActId");

        Image image = new Image();
        image.setImagename(ActId);
        List<Image> imageList = new ArrayList<Image>();
        imageList = imageService.queryAllByLike(image);

        response.setContentType("application/json;charset=UTF-8");
        try {
            PrintWriter out = response.getWriter();
            out.write(JSON.toJSONString(imageList));
            System.out.println(JSON.toJSONString(imageList));
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/GetArticleImg")
    public void GetArticleImg(HttpServletRequest request, HttpServletResponse response){
        String ArticleId = (String) request.getSession().getAttribute("ArticleId");
        ArticleId = "-" + ArticleId.substring(ArticleId.length()-6, ArticleId.length());
        Image image = new Image();
        image.setImagename(ArticleId);
        List<Image> imageList = new ArrayList<Image>();
        imageList = imageService.queryAllByLike(image);

        response.setContentType("application/json;charset=UTF-8");
        try {
            PrintWriter out = response.getWriter();
            out.write(JSON.toJSONString(imageList));
            System.out.println(JSON.toJSONString(imageList));
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 取出所有图片，仅测试使用
     */
    @RequestMapping("/GetAllImg")
    public void GetAllImg(HttpServletResponse response){
        List<Image> imageList = new ArrayList<Image>();
        imageList = imageService.queryAllByLimit(0,3);

        response.setContentType("application/json;charset=UTF-8");
        try {
            PrintWriter out = response.getWriter();
            out.write(JSON.toJSONString(imageList));
            System.out.println(JSON.toJSONString(imageList));
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Image selectOne(String id) {
        return this.imageService.queryById(id);
    }

}