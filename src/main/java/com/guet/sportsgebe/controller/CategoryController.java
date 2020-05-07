package com.guet.sportsgebe.controller;

import com.guet.sportsgebe.entity.Category;
import com.guet.sportsgebe.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * (Category)表控制层
 *
 * @author makejava
 * @since 2020-04-21 23:27:44
 */
@Controller
@RequestMapping("/Category")
public class CategoryController {
    /**
     * 服务对象
     */
    @Resource
    private CategoryService categoryService;

    @RequestMapping("/GetActCate")
    public void GetActCate(HttpServletRequest request, HttpServletResponse response){
        String ActId = (String) request.getSession().getAttribute("ActId");

    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Category selectOne(String id) {
        return this.categoryService.queryById(id);
    }

}