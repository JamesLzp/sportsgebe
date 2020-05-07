package com.guet.sportsgebe.controller;

import com.alibaba.fastjson.JSON;
import com.guet.sportsgebe.entity.ActCate;
import com.guet.sportsgebe.entity.Category;
import com.guet.sportsgebe.service.ActCateService;
import com.guet.sportsgebe.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * (ActCate)表控制层
 *
 * @author makejava
 * @since 2020-04-21 22:24:31
 */
@Controller
@RequestMapping("/ActCate")
public class ActCateController {
    /**
     * 服务对象
     */
    @Resource
    private ActCateService actCateService;
    @Resource
    private CategoryService categoryService;

    @RequestMapping("/GetActCategory")
    public void GetActCategory(HttpServletRequest request, HttpServletResponse response){
        String ActId = request.getParameter("ActId");
        List<ActCate> actCateList = new ArrayList<ActCate>();
        actCateList = actCateService.queryById(ActId);

        List tempCateList = new ArrayList();
        for (ActCate temp: actCateList) {
            tempCateList.add(temp.getCateid());
        }

        List<Category> categoryList = new ArrayList<Category>();
        categoryList = categoryService.queryByInIds(tempCateList);

        response.setContentType("text/html;charset=UTF-8");
        try {
            PrintWriter out = response.getWriter();
            out.write(JSON.toJSONString(categoryList));
            System.out.println("Activity Category");
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}