package com.guet.sportsgebe.controller;

import com.alibaba.fastjson.JSON;
import com.guet.sportsgebe.entity.Comment;
import com.guet.sportsgebe.entity.GeneralAdmin;
import com.guet.sportsgebe.entity.SuperAdmin;
import com.guet.sportsgebe.service.CommentService;
import com.guet.sportsgebe.service.GeneralAdminService;
import com.guet.sportsgebe.service.SuperAdminService;
import com.guet.sportsgebe.service.UsersService;
import com.guet.sportsgebe.util.GetCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * (SuperAdmin)表控制层
 *
 * @author makejava
 * @since 2020-05-07 21:48:37
 */
@Controller
@RequestMapping("/SuperAdmin")
public class SuperAdminController {
    /**
     * 服务对象
     */
    @Resource
    private SuperAdminService superAdminService;
    @Resource
    private UsersService usersService;
    @Resource
    private GeneralAdminService generalAdminService;
    @Resource
    private CommentService commentService;

    @RequestMapping("/SALogin")
    public String GALogin(){
        return "SALogin";
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

    @RequestMapping("/GetSALogin")
    public void GetGALogin(HttpServletRequest request, HttpServletResponse response){
        String SAUserName = request.getParameter("UserName");
        String SAPassword = request.getParameter("Password");

        response.setContentType("text/plain");
        String responseText = null;
        if(SAUserName != null && SAPassword != null){
            SuperAdmin superAdmin = superAdminService.queryByName(SAUserName);
            if(SAUserName.equals(superAdmin.getSadminname())){
                if(SAPassword.equals(superAdmin.getSapassword())){
                    superAdminService.update(superAdmin);

                    responseText = "LoginSuccess";
                }else {
                    responseText = "PasswordFailed";
                }
            }else {
                responseText = "UserNameFailed";
            }
        }else{
            responseText = "UserNameFailed";
        }

        ResponseParam(response, responseText);
    }

    @RequestMapping("/GetAdminApply")
    public void GetAdminApply(HttpServletResponse response){
        List<GeneralAdmin> list = new ArrayList<GeneralAdmin>();

        GeneralAdmin ga = new GeneralAdmin();
        ga.setGadminstate("unCheck");

        list = generalAdminService.queryAll(ga);

        response.setContentType("text/html;charset=UTF-8");
        ResponseParam(response, JSON.toJSONString(list));
    }

    @RequestMapping("/AgreeLeaderApply")
    public void AgreeLeaderApply(HttpServletRequest request, HttpServletResponse response){
        String UserId = request.getParameter("UserId");

        GeneralAdmin ga = new GeneralAdmin();
        ga.setGadminstate("agree");
        ga.setGadminid(UserId);

        ga = generalAdminService.update(ga);

        Comment comment = new Comment();
        comment.setCommentid(GetCode.Get6Code());
        comment.setContent("你的领队已通过，请多创建活动");
        comment.setCate("System");
        comment.setUserid(UserId);

        response.setContentType("text/plain");
        ResponseParam(response, "ok");
    }

    @RequestMapping("/DisAgreeLeaderApply")
    public void DisAgreeLeaderApply(HttpServletRequest request, HttpServletResponse response){
        String UserId = request.getParameter("UserId");

        generalAdminService.deleteById(UserId);

        Comment comment = new Comment();
        comment.setCommentid(GetCode.Get6Code());
        comment.setContent("你的领队申请未通过，请多参加活动，再次申请");
        comment.setCate("System");
        comment.setUserid(UserId);

        response.setContentType("text/plain");
        ResponseParam(response, "ok");
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
    public SuperAdmin selectOne(String id) {
        return this.superAdminService.queryById(id);
    }

}