package com.guet.sportsgebe.controller;

import com.guet.sportsgebe.entity.GeneralAdmin;
import com.guet.sportsgebe.entity.Users;
import com.guet.sportsgebe.service.GeneralAdminService;
import com.guet.sportsgebe.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * (GeneralAdmin)表控制层
 *
 * @author makejava
 * @since 2020-05-05 14:39:28
 */
@Controller
@RequestMapping("/GeneralAdmin")
public class GeneralAdminController {
    /**
     * 服务对象
     */
    @Resource
    private GeneralAdminService generalAdminService;
    @Resource
    private UsersService usersService;

    @RequestMapping("/GALogin")
    public String GALogin(){
        return "GALogin";
    }

    @RequestMapping("/LeaderApply")
    public String LeaderApply(){
        return "LeaderApply";
    }

    @RequestMapping("/ActivityApply")
    public String ActivityApply(){
        return "ActivityApply";
    }

    @RequestMapping("/ArticleApply")
    public String ArticleApply(){
        return "ArticleApply";
    }

    @RequestMapping("/GoCheckActivity")
    public String CheckActivity(HttpServletRequest request){
//        request.getSession().setAttribute("CheckActId", request.getParameter("ActId"));
        return "CheckActivity";
    }

    @RequestMapping("/GoCheckArticle")
    public String GoCheckArticle(HttpServletRequest request){
//        request.getSession().setAttribute("CheckActId", request.getParameter("ActId"));
        return "CheckArticle";
    }

    @RequestMapping("/GetGALogin")
    public String GetGALogin(HttpServletRequest request, HttpServletResponse response){

        return "GAindex";
    }

    public void ResponseParam(HttpServletResponse response, String param){
        try {
            PrintWriter out = response.getWriter();
            out.write(param);
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
    public GeneralAdmin selectOne(String id) {
        return this.generalAdminService.queryById(id);
    }

}