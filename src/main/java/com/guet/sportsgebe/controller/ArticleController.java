package com.guet.sportsgebe.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.guet.sportsgebe.entity.*;
import com.guet.sportsgebe.service.*;
import com.guet.sportsgebe.util.File;
import com.guet.sportsgebe.util.GetCode;
import org.apache.commons.collections.ArrayStack;
import org.omg.CORBA.NO_IMPLEMENT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.security.krb5.internal.crypto.Des;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

/**
 * (Article)表控制层
 *
 * @author makejava
 * @since 2020-04-19 19:53:52
 */
@Controller
@RequestMapping(value = "/Article")
public class ArticleController {
    /**
     * 服务对象
     */
    @Resource
    private ArticleService articleService;
    @Resource
    private FollowListService followListService;
    @Resource
    private ActivityService activityService;
    @Resource
    private UsersService usersService;
    @Resource
    private ImageService imageService;

    @Value("${web.upload-path}")
    private String path;

    @RequestMapping("/GoCreateArticle")
    public String GoCreateArticle(){
        return "createArticle";
    }

    @RequestMapping("/GoShowArticle")
    public String GoShowArticle(HttpServletRequest request){
        request.getSession().setAttribute("ArticleId", request.getParameter("ArticleId"));
        return "showArticle";
    }

    @RequestMapping("/GoOtherArticle")
    public String GoOtherArticle(HttpServletRequest request, HttpServletResponse response){
        request.getSession().setAttribute("OtherArtId", request.getParameter("UserId"));
        return "article";
    }

    @RequestMapping("/GetAllDes")
    public void GetAllDes(HttpServletRequest request, HttpServletResponse response){
        String ActId = (String) request.getSession().getAttribute("ActId");

        String Description = File.getTXTFile(path + "Des/" + ActId + "-Des.txt");
        String TripDes = File.getTXTFile(path + "Des/" + ActId + "-TripDes.txt");
        String OutfitDes = File.getTXTFile(path + "Des/" + ActId + "-OutfitDes.txt");
        String NoticeDes = File.getTXTFile(path + "Des/" + ActId + "-NoticeDes.txt");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("actdescription",Description);
        jsonObject.put("acttripdes",TripDes);
        jsonObject.put("actoutfitdes",OutfitDes);
        jsonObject.put("actnoticedes", NoticeDes);

        response.setContentType("text/html;charset=UTF-8");
        ResponseParam(response, JSON.toJSONString(jsonObject));

    }

    @RequestMapping("/GetFollowArticle")
    public void GetFollowArticle(HttpServletRequest request, HttpServletResponse response){
        String Other = request.getParameter("Other");
        String UserId;
        if(Other == null)
            UserId = (String) request.getSession().getAttribute("userId");
        else
            UserId = (String) request.getSession().getAttribute("OtherUserId");

        List<FollowList> followLists = new ArrayList<FollowList>();
        followLists = followListService.queryById(UserId);
        List tempList = new ArrayList();
        for (FollowList temp : followLists){
            tempList.add(temp.getFollowerid());
        }

        List<Users> usersList = new ArrayList<Users>();
        usersList = usersService.queryByInIds(tempList);

        List tempNameList = new ArrayList();
        for (Users users : usersList){
            tempNameList.add(users.getUserrealname());
        }
        tempNameList.add(request.getSession().getAttribute("userRealName"));

        List<Activity> activities = new ArrayList<Activity>();
        activities = activityService.queryByInIds(tempNameList);

        Users user = new Users();
        user = usersService.queryById(UserId);
        Iterator<Activity> it = activities.iterator();
        while(it.hasNext()){
            Activity temp = it.next();
            if(temp.getActstate().equals("over") || temp.getActivityid().equals(user.getTopactid())){
                it.remove();
            }
        }

        Collections.sort(activities);       //根据日期排序

        response.setContentType("text/html;charset=UTF-8");
        try {
            PrintWriter out = response.getWriter();
            out.write(JSON.toJSONString(activities));
            System.out.println("FollowList Activity");
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/GetOneDes")
    public void GetOneDes(HttpServletRequest request, HttpServletResponse response){
        String ActId = request.getParameter("ActId");
        String Description = File.getTXTFile(path + "Des/" + ActId + "-Des.txt");
        String str = Description;

        int len = 200;

        if (Description != null){
            byte[] bt = Description.getBytes();
            if(bt.length > len){
                if(bt[len - 1] < 0){
                    str = new String(bt, 0, len-1);
                }else {
                    str = new String(bt,0,len);
                }
            }
        }
        System.out.println(str);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("actdescription",str);
        response.setContentType("text/html;charset=UTF-8");
        ResponseParam(response, JSON.toJSONString(jsonObject));
    }

    @RequestMapping("/GetIntro")
    public void GetIntro(HttpServletRequest request, HttpServletResponse response){
        String ActId = request.getParameter("ActId");
        if (ActId == null || ActId.isEmpty())
            ActId = (String) request.getSession().getAttribute("ActId");
        Article article = new Article();
        article = articleService.queryById(ActId);
        System.out.println(ActId);
        response.setContentType("text/html;charset=UTF-8");
        ResponseParam(response, JSON.toJSONString(article));
        System.out.println("GetIntro: " + JSON.toJSONString(article));
    }

    @RequestMapping("/GetAllArticle")
    public void GetAllArticle(HttpServletRequest request, HttpServletResponse response){
        String UserId = (String) request.getSession().getAttribute("OtherArtId");
        response.setContentType("text/html;charset=UTF-8");

        List<Article> articleList = new ArrayList<Article>();
        Article article = new Article();
        article.setArticlecate("Article");
        if(UserId != null){
            Users user = usersService.queryById(UserId);
            articleList = articleService.queryByIdLike(user.getUserrealname());
            Collections.sort(articleList);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("art", articleList);
            jsonObject.put("otherName", user.getUsername());
            request.getSession().removeAttribute("OtherArtId");

            ResponseParam(response, JSON.toJSONString(jsonObject));
        }else {
            articleList = articleService.queryAll(article);
            Collections.sort(articleList);

            ResponseParam(response, JSON.toJSONString(articleList));
        }
    }

    @RequestMapping("/GetOwnArticle")
    public void GetOwnArticle(HttpServletRequest request, HttpServletResponse response){
        String RealName = (String) request.getSession().getAttribute("userRealName");
        String UserId = (String) request.getSession().getAttribute("userId");

        List<Article> articleList = new ArrayList<Article>();
        articleList = articleService.queryByIdLike(RealName);

        Users user = new Users();
        user = usersService.queryById(UserId);
        Iterator<Article> it = articleList.iterator();
        while(it.hasNext()){
            Article temp = it.next();
            if(temp.getArticleid().equals(user.getToparticleid())){
                it.remove();
            }
        }

        Collections.sort(articleList);

        response.setContentType("text/html;charset=UTF-8");
        ResponseParam(response, JSON.toJSONString(articleList));
    }

    @RequestMapping("/GetTopArticle")
    public void GetTopArticle(HttpServletResponse response, HttpServletRequest request){
        String UserId = (String) request.getSession().getAttribute("userId");

        Users user = usersService.queryById(UserId);

        Article article = articleService.queryByIds(user.getToparticleid());

        response.setContentType("text/html;charset=UTF-8");
        ResponseParam(response, JSON.toJSONString(article));
    }

    @RequestMapping("/createArticle")
    @ResponseBody
    public void CreateAct(@RequestParam("file") MultipartFile[] img, HttpServletRequest request, HttpServletResponse response) {
        Article article = new Article();
        String RealName = (String) request.getSession().getAttribute("userRealName");

        String ID = GetCode.Get6Code();
        article.setArticleid(RealName + "-" + ID);
        article.setArticlename(request.getParameter("ArticleName"));
        article.setArticlecate("Article");
        article.setIshotarticle("no");
        article.setArticleintro(request.getParameter("ArticleIntro"));

        if (img.length != 0){
            Image image = new Image();
            for (int i = 0; i < img.length; i++) {
                String FileName = "Ar" +  i + "-" + ID + ".jpg";
                String newFilePath =path + "images/" + FileName;
                try {
                    InputStream inputStream1 = img[i].getInputStream();
                    File.saveStreamFile(inputStream1,newFilePath);

                    image.setImagename(FileName);
                    image.setImageid(GetCode.Get6Code());
                    imageService.insert(image);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        File.saveTXTFile(request.getParameter("ArticleDescription"),path + "Des/" + RealName + "-" + ID + "-Art.txt");

        articleService.insert(article);

        response.setContentType("text/plain");
        ResponseParam(response, "Success");
    }

    @RequestMapping("/GetArticle")
    public void GetArticle(HttpServletRequest request, HttpServletResponse response){
        String ArticleId = (String) request.getSession().getAttribute("ArticleId");

        Article article = articleService.queryByIds(ArticleId);

        System.out.println(JSON.toJSONString(article));
        response.setContentType("text/html;charset=UTF-8");
        ResponseParam(response, JSON.toJSONString(article));
    }

    @RequestMapping("/GetArticleDes")
    public void GetArticleDes(HttpServletResponse response, HttpServletRequest request){
        String ArticleId = (String) request.getSession().getAttribute("ArticleId");

        String Des = File.getTXTFile(path + "Des/" + ArticleId + "-Art.txt");

        response.setContentType("text/html;charset=UTF-8");
        ResponseParam(response, Des);
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
    public Article selectOne(String id) {
        return this.articleService.queryById(id);
    }

}