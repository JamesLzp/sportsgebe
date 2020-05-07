package com.guet.sportsgebe.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.guet.sportsgebe.entity.*;
import com.guet.sportsgebe.service.*;
import com.guet.sportsgebe.util.File;
import com.guet.sportsgebe.util.GetCode;
import com.guet.sportsgebe.util.MyDictionary;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.jws.soap.SOAPBinding;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * (Users)表控制层
 *
 * @author makejava
 * @since 2020-03-15 01:52:14
 */
@Controller
@RequestMapping(value = "/Users")
public class UsersController {
    /**
     * 服务对象
     */
    @Resource
    private UsersService usersService;
    @Resource
    private FollowListService followListService;
    @Resource
    private ActivityService activityService;
    @Resource
    private ImageService imageService;
    @Resource
    private GeneralAdminService generalAdminService;

    @Value("${web.upload-path}")
    private String path;

    @RequestMapping("/Test")
    public String Test(){
        return "YY";
    }

    /**
     * 首页
     */
    @RequestMapping("/user")
    public String UserIndex(){
        return "index";
    }

    /**
     * 跳转到注册页面
     * @return
     */
    @RequestMapping("/GoRegister")
    public String GoToRegister(){
        return "register";
    }

    /**
     * 跳转到about界面
     * @return
     */
    @RequestMapping("/GoAbout")
    public String GoToAbout(){
        return "about";
    }

    /**
     * 跳转到服务界面
     * @return
     */
    @RequestMapping("/GoServices")
    public String GoToService(){
        return "services";
    }

    /**
     * 跳转到文章界面
     * @return
     */
    @RequestMapping("/GoArticle")
    public String GoToArticle(){
        return "article";
    }

    /**
     * 跳转到日志界面
     * @return
     */
    @RequestMapping("/GoBlog")
    public String GoToBlog(){
        return "article";
    }

    /**
     * 跳转到个人页面
     * @return
     */
    @RequestMapping("/GoPerson")
    public String GoToPerson(){
        return "PersonAct";
    }

    @RequestMapping("/GoOtherPerson")
    public String GoOtherPerson(HttpServletRequest request){
        request.getSession().setAttribute("OtherUserId", request.getParameter("UserId"));
        return "OtherPerson";
    }

    @RequestMapping("/GoLeaderApply")
    public String GoLeaderApply(){
        return "UserLeaderApply";
    }

    @RequestMapping("/Logout")
    public String Logout(HttpServletRequest request){
        request.getSession().removeAttribute("name");
        request.getSession().removeAttribute("userId");
        request.getSession().removeAttribute("userRealName");
        return "index";
    }

    /**
     * 取得所有注册信息进行处理验证
     * @param userMessage
     * @return
     */
    @PostMapping("/Register")
    public String GetRegister(@RequestParam Map<String,Object> userMessage){
        Users user = new Users();
        user.setUserid(GetCode.Get6Code());
        user.setUsername((String) userMessage.get("Name"));
        user.setUserrealname((String) userMessage.get("Realname"));
        user.setUserpassword((String) userMessage.get("Password"));
        user.setUserphone((String) userMessage.get("Phone"));
        user.setUsermail((String) userMessage.get("Email"));
        user.setUserphotoid(MyDictionary.DefaultUserPhotoId);
        user.setIsadmin(MyDictionary.DefaultIsAdmin);
        user.setTopactid(MyDictionary.DefaultTopActId);
        user.setToparticleid(MyDictionary.DefaultTopArticleId);
        usersService.insert(user);

        return "index";
    }

    /**
     * 测试session和cookie如何运行
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/UserLogin")
    public void UserLogin(HttpServletRequest request, HttpServletResponse response){
        String userName = request.getParameter("Name");
        String userPassword = request.getParameter("Password");

        try {
            response.setContentType("text/plain");
            PrintWriter out = response.getWriter();

            Users user = usersService.queryByName(userName);
            if(user != null){
                if(user.getUserpassword().equals(userPassword)){
                    request.getSession().setAttribute("name", userName);
                    request.getSession().setAttribute("userId", user.getUserid());  //设置session，方便后端检查是否登录
                    request.getSession().setAttribute("userRealName", user.getUserrealname());

                    Cookie nameCookie=new Cookie("userName",userName);  //设置cookie，方便前端显示
                    nameCookie.setPath("/");
                    response.addCookie(nameCookie);
                    Cookie realName=new Cookie("realName",user.getUserrealname());  //设置cookie，方便前端显示
                    realName.setPath("/");
                    response.addCookie(realName);
                    Cookie passwordCookie=new Cookie("userPassword",userPassword);  //设置cookie，方便前端显示
                    passwordCookie.setPath("/");
                    response.addCookie(passwordCookie);

                    out.write("LoginSuccess");
                }else{
                    out.write("PasswordFailed");
                }
            }
            else{
                out.write("UserNameFailed");
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/CheckLogin")
    public void CheckLogin(HttpServletRequest request, HttpServletResponse response){
        String userName = request.getParameter("Name");
        String userPassword = request.getParameter("Password");

        try {
            response.setContentType("text/plain");
            PrintWriter out = response.getWriter();

            Users user = usersService.queryByName(userName);
            if(user != null){
                if(user.getUserpassword().equals(userPassword)){
                    out.write("LoginSuccess");
                }else{
                    out.write("PasswordFailed");
                }
            }
            else{
                out.write("UserNameFailed");
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/GetLeaderPhone")
    public void GetLeaderPhone(HttpServletRequest request, HttpServletResponse response){
        String RealName = request.getParameter("UserRealName");
        System.out.println(RealName);
        String UserId = (String) request.getSession().getAttribute("userId");
        Users user = new Users();
        user.setUserrealname(RealName);
        List<Users> usersList = new ArrayList<Users>();
        usersList = usersService.queryAll(user);
        Users NewUser = new Users();
        for (Users u : usersList){
            NewUser = u;
            System.out.println("yyyyy:" + JSON.toJSONString(u));
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userid", NewUser.getUserid());
        jsonObject.put("phone", NewUser.getUserphone());
        System.out.println(UserId + " " + NewUser.getUserid());
        if(!NewUser.getUserid().equals(UserId) && UserId != null){
            jsonObject.put("followState", "no");
            List<FollowList> followLists = new ArrayList<FollowList>();
            followLists = followListService.queryById(UserId);
            for (FollowList temp : followLists){
                if(temp.getFollowerid().equals(NewUser.getUserid())){
                    jsonObject.remove("followState");
                    jsonObject.put("followState", "yes");
                    break;
                }
            }
        }else {
            jsonObject.put("followState", "sameUser");
        }

        response.setContentType("application/json;charset=UTF-8");
        ResponseParam(response, JSON.toJSONString(jsonObject));
    }

    @RequestMapping("/GetHot4Act")
    public void GetHot4Act(HttpServletResponse response){
        List<Activity> activityList = new ArrayList<Activity>();
        Activity activity = new Activity();
        activity.setIshotlist("yes");
        //activity.setActstate("signing");
        activityList = activityService.queryAll(activity);

        System.out.println("Get Hot 4 Activity");
        response.setContentType("application/json;charset=UTF-8");
        ResponseParam(response, JSON.toJSONString(activityList));
    }

    @RequestMapping("/GetNew4Act")
    public void GetNew4Act(HttpServletResponse response){
        List<Activity> activityList = new ArrayList<Activity>();
        activityList = activityService.queryAllByLimit(0, 4);

        System.out.println("Get New 4 Activity");
        response.setContentType("application/json;charset=UTF-8");
        ResponseParam(response, JSON.toJSONString(activityList));
    }

    @RequestMapping("/SetOnTop")
    public void SetOnTop(HttpServletRequest request, HttpServletResponse response){
        String UserId = (String) request.getSession().getAttribute("userId");
        Users user = new Users();
        if(request.getParameter("ActId") != null){
            user.setTopactid(request.getParameter("ActId"));
        }else if(request.getParameter("ArticleId") != null){
            user.setToparticleid(request.getParameter("ArticleId"));
        }
        user.setUserid(UserId);
        user = usersService.updateTop(user);
        System.out.println("updateTop: " + JSON.toJSONString(user));
        response.setContentType("text/plain");
        ResponseParam(response, "Success");
    }

    @RequestMapping("/CancelOnTop")
    public void CancelOnTop(HttpServletRequest request, HttpServletResponse response){
        String UserId = (String) request.getSession().getAttribute("userId");
        Users user = new Users();
        user.setUserid(UserId);

        if("yes".equals(request.getParameter("Act"))){
            user.setTopactid("");
        }else if("yes".equals(request.getParameter("Article"))){
            user.setToparticleid("");
        }

        user = usersService.updateTop(user);
        System.out.println("updateTop: " + JSON.toJSONString(user));
        response.setContentType("text/plain");
        ResponseParam(response, "Success");
    }

    @RequestMapping("/ChangeUserImg")
    public void ChangeUserImg(@RequestParam("UserImg") MultipartFile[] UserImg,HttpServletRequest request, HttpServletResponse response){
        String UserId = (String) request.getSession().getAttribute("userId");
        String UserImgId = GetCode.Get6Code();
        String UserImgName = UserId + "-USI.jpg";
        String ImgPath = path + "images/" + UserImgName;

        InputStream inputStream = null;
        try {
            inputStream = UserImg[0].getInputStream();
            File.saveStreamFile(inputStream,ImgPath);

            Image image = new Image();
            image.setImageid(UserImgId);
            image.setImagename(UserImgName);
            imageService.insert(image);

            Users user = new Users();
            user.setUserphotoid(UserImgId);
            user.setUserid(UserId);
            usersService.update(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.setContentType("text/plain");
        ResponseParam(response, UserImgName);
    }

    @RequestMapping("/GetUserImg")
    public void GetUserImg(HttpServletRequest request, HttpServletResponse response){
        String Other = request.getParameter("Other");
        String UserId;
        if(Other == null)
            UserId = (String) request.getSession().getAttribute("userId");
        else
            UserId = (String) request.getSession().getAttribute("OtherUserId");

        Users user = new Users();
        user = usersService.queryById(UserId);
        String UserImg;
        if(user.getUserphotoid().equals("000001")){
            UserImg = "DefaultUPId.jpg";
        }else {
            UserImg = UserId + "-USI.jpg";
        }
        response.setContentType("text/plain");
        ResponseParam(response, UserImg);
    }

    @RequestMapping("/GetFollowList")
    public void GetFollowList(HttpServletRequest request, HttpServletResponse response){
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
        if(!tempList.isEmpty()){
            usersList = usersService.queryByInIds(tempList);
        }

        response.setContentType("application/json;charset=UTF-8");
        ResponseParam(response, JSON.toJSONString(usersList));
    }

    @RequestMapping("/DeleteFollow")
    public void DeleteFollow(HttpServletRequest request, HttpServletResponse response){
        String UserId = request.getParameter("UserId");

        followListService.deleteByFollowId(UserId);
        response.setContentType("text/plain");
        ResponseParam(response, "Success");
    }

    @RequestMapping("/IsFollow")
    public void IsFollow(HttpServletResponse response, HttpServletRequest request){
        String UserId = (String) request.getSession().getAttribute("userId");
        String OtherId = (String) request.getSession().getAttribute("OtherUserId");

        FollowList followList = new FollowList();
        followList.setUserid(UserId);
        followList.setFollowerid(OtherId);

        response.setContentType("text/plain");
        if(!followListService.queryAll(followList).isEmpty()){
            ResponseParam(response, "yes");
        }else {
            ResponseParam(response, "no");
        }
    }

    @RequestMapping("/AddFollow")
    public void AddFollow(HttpServletResponse response, HttpServletRequest request){
        String Other = request.getParameter("UserId");
        String UserId = (String) request.getSession().getAttribute("userId");

        FollowList followList = new FollowList();
        followList.setUserid(UserId);
        followList.setFollowerid(Other);
        followListService.insert(followList);

        response.setContentType("text/plain");
        ResponseParam(response, "Success");
    }

    @RequestMapping("/LeaderApply")
    public String LeaderApply(HttpServletRequest request){
        GeneralAdmin ga = new GeneralAdmin();
        ga.setGadminid((String) request.getSession().getAttribute("userId"));
        ga.setIdcard(request.getParameter("ID"));
        ga.setGadminintro(request.getParameter("UserIntro"));
        ga.setGadminmail(request.getParameter("Email"));
        ga.setGadminname(request.getParameter("RealName"));
        ga.setGadminphone(request.getParameter("Phone"));
        ga.setGadminstate("unCheck");

        generalAdminService.insert(ga);

        Users user = new Users();
        user.setUserid((String) request.getSession().getAttribute("userId"));
        user.setIsadmin("unCheck");
        usersService.update(user);

        return "GoPerson";
    }

    /**
     * 通过主键查询单条数据
     */
    @RequestMapping("/GetUserDes")
    public void GetUserDes(HttpServletRequest request, HttpServletResponse response) {
        String UserId = (String) request.getSession().getAttribute("userId");
        Users user = usersService.queryById(UserId);
        response.setContentType("application/json;charset=UTF-8");
        ResponseParam(response, JSON.toJSONString(user));
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

}