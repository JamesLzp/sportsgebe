package com.guet.sportsgebe.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.guet.sportsgebe.entity.*;
import com.guet.sportsgebe.service.*;
import com.guet.sportsgebe.util.File;
import com.guet.sportsgebe.util.GetCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * (Activity)表控制层
 *
 * @author makejava
 * @since 2020-04-12 00:45:08
 */
@Controller
@RequestMapping(value = "/Activity")
public class ActivityController {
    /**
     * 服务对象
     */
    @Resource
    private ActivityService activityService;
    @Resource
    private ImageService imageService;
    @Resource
    private ArticleService articleService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private ActCateService actCateService;
    @Resource
    private ActUserService actUserService;
    @Resource
    private UsersService usersService;


    @Autowired
    AliPayService aliPayService;

    @Value("${web.upload-path}")
    private String path;

    @RequestMapping("/GoCreateAct")
    public String GotoCreateAct(){
        return "createAct";
    }

    @RequestMapping("/GoShowAct")
    public String GoToShowAct(HttpServletRequest request){
        request.getSession().setAttribute("ActId",request.getParameter("ActId"));
        return "showAct";
    }

    @RequestMapping("/GoChangeAct")
    public String GoToChangeAct(HttpServletRequest request){
        request.getSession().setAttribute("ActId", request.getParameter("ActId"));
        return "changeAct";
    }

    @RequestMapping("/GetShowActDes")
    public void GetActDes(HttpServletRequest request, HttpServletResponse response){
        String ActId = (String) request.getSession().getAttribute("ActId");
        Activity activity = activityService.queryById(ActId);
        System.out.println(JSON.toJSONString(activity));
        response.setContentType("application/json;charset=UTF-8");
        try {
            PrintWriter out = response.getWriter();
            out.write(JSON.toJSONString(activity));
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建活动，获取所有页面内容
     * @param img
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/createAct")
    @ResponseBody
    public void CreateAct(@RequestParam("showFile") MultipartFile[] showImg, @RequestParam("file") MultipartFile[] img, HttpServletRequest request, HttpServletResponse response){
        Activity activity = new Activity();
        String activityId = GetCode.Get6Code();

        PrintWriter out = null;
        try {
            response.setContentType("text/plain");
            out = response.getWriter();

            //处理封面图片
            String showFileName = activityId + "-" + "ASI.jpg";
            String newShowFilePath = path + "images/" + showFileName;
            InputStream inputStream = showImg[0].getInputStream();
            File.saveStreamFile(inputStream,newShowFilePath);

            Image image = new Image();
            image.setImagename(showFileName);
            image.setImageid(GetCode.Get6Code());
            imageService.insert(image);

            //处理活动展示图片
            for (int i = 0; i < img.length; i++) {
                String FileName = activityId + "-" + i + ".jpg";
                String newFilePath =path + "images/" + FileName;

                InputStream inputStream1 = img[i].getInputStream();
                File.saveStreamFile(inputStream1,newFilePath);

                //图片存入数据库
                image.setImagename(FileName);
                image.setImageid(GetCode.Get6Code());
                imageService.insert(image);
            }

            //文章Intro
            String Intro = request.getParameter("ActIntro");
            String Description = request.getParameter("ActDescription");
            Article article = new Article();
            article.setArticleintro(Intro);
            article.setArticleid(GetCode.Get6Code() + "-" + activityId);
            article.setArticlename(activityId + "-Des");
            article.setIshotarticle("no");
            article.setArticlecate("ActivityIntro");
            articleService.insert(article);

            //处理文本描述
            File.saveTXTFile(Description,path + "Des/" + activityId + "-Des.txt");
            File.saveTXTFile(request.getParameter("ActTripDes"),path + "Des/" + activityId + "-TripDes.txt");
            File.saveTXTFile(request.getParameter("ActOutfitDes"),path + "Des/" + activityId + "-OutfitDes.txt");
            File.saveTXTFile(request.getParameter("ActNotice"),path + "Des/" + activityId + "-NoticeDes.txt");

            //类别处理
            String Categories = request.getParameter("Category");
            String[] cateArray = Categories.split("/");

            //类别名存到 list 中
            List list = new ArrayList();
            for(int i = 0; i < cateArray.length; i++){
                list.add(cateArray[i]);
            }
            //类别名存在于数据库的就取出来
            List<Category> tempList = new ArrayList<Category>();
            tempList = categoryService.queryByInNames(list);
            List<ActCate> actcatelist = new ArrayList<ActCate>();

            for(Category temp : tempList){
                if(list.contains(temp.getCatename())){
                    list.remove(temp.getCatename());
                    actcatelist.add(new ActCate(activityId, temp.getCateid()));
                }
            }

            List<Category> catelist = new ArrayList<Category>();

            for (Object temp : list) {
                String cateId = GetCode.Get6Code();
                catelist.add(new Category(cateId, temp.toString()));
                actcatelist.add(new ActCate(activityId, cateId));
            }
            if(!catelist.isEmpty())
                categoryService.insert(catelist);

            activity.setActivityid(activityId);
            activity.setActname(request.getParameter("ActName"));
            activity.setOriginatorname((String) request.getSession().getAttribute("userRealName"));
            activity.setDestination(request.getParameter("Destination"));
            activity.setDepartplace(request.getParameter("DepartPlace"));
            activity.setActdays(Integer.valueOf(request.getParameter("ActDays")));
            activity.setActspend(Double.valueOf(request.getParameter("ActSpend")));
            activity.setVehicle(request.getParameter("Vehicle"));

            Date sql2 = Date.valueOf(request.getParameter("StartTime"));
            activity.setStarttime(sql2);

            activity.setExpectplayer(Integer.valueOf(request.getParameter("ExpectPlayer")));
            activity.setActShowImgName(showFileName);
            activity.setActstate("signing");
            activity.setActdescriptionid(activityId + "-Des");
            activity.setActtripdesid(activityId + "-TripDes");
            activity.setActoutfitdesid(activityId + "-OutfitDes");
            activity.setActnoticeid(activityId + "-NoticeDes");
            activity.setEnlistplayer(1);
            activity.setHits(0);
            activity.setIshotlist("yes");
            activity.setRespond(0);

            activityService.insert(activity);

            ActUser actUser = new ActUser();
            actUser.setActid(activityId);
            actUser.setUserid((String) request.getSession().getAttribute("userId"));
            actUser.setIsleader("yes");
            actUser.setAttendstate("Enlist");
            actUser.setAmountpaid(Double.valueOf(request.getParameter("ActSpend")));
            actUserService.insert(actUser);

            actCateService.inserts(actcatelist);

            out.write("success");
        }catch (IOException e){
            e.printStackTrace();
            out.write("false");
        }
        out.flush();
        out.close();
    }


    /**
     * 查询活动的简述介绍
     * @param response
     */
    @RequestMapping("/GetAllActDes")
    public void GetAllActDes(HttpServletResponse response){
        List<Activity> ActList = new ArrayList<Activity>();

        Activity act = new Activity();
        act.setActstate("signing");
        ActList = activityService.queryAll(act);
        System.out.println(JSON.toJSONString(ActList));

        response.setContentType("application/json;charset=UTF-8");
        try {
            PrintWriter out = response.getWriter();
            out.write(JSON.toJSONString(ActList));
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/Enlist")
    public void Enlist(HttpServletRequest request, HttpServletResponse response) {
        String ActId = request.getParameter("ActId");
        String RealName = (String) request.getSession().getAttribute("userRealName");
        String ActName = request.getParameter("ActId");
        Double ActSpend = Double.valueOf(request.getParameter("ActSpend"));

        request.getSession().setAttribute("ActName", activityService.queryById(ActId).getActname());
        request.getSession().setAttribute("ActSpend", ActSpend);

//        ActUser actUser = new ActUser();
//        actUser.setActid(ActId);
//        actUser.setUserid(UserId);
//        actUser.setAmountpaid(ActSpend);
//        actUser.setAttendstate("Enlist");
//        actUser.setIsleader("no");
//
//        actUserService.insert(actUser);
//
//
//        Activity activity = new Activity();
//        activity = activityService.queryById(ActId);
//        Activity newAct = new Activity();
//        newAct.setActivityid(ActId);
//        newAct.setEnlistplayer(activity.getEnlistplayer() + 1);
//        activityService.update(newAct);
    }

    @RequestMapping("/TestAlipay")
    public String TestAlipay(HttpServletRequest request, HttpServletResponse response){
        return "PayTemp";
    }

    @RequestMapping("/TempPay")
    public void TempPay(HttpServletRequest request, HttpServletResponse response){
        System.out.println(request.getAttribute("ActName"));
        System.out.println(request.getAttribute("ActSpend"));
        System.out.println(request.getSession().getAttribute("userRealName"));

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ActName", request.getSession().getAttribute("ActName"));
        jsonObject.put("ActSpend", request.getSession().getAttribute("ActSpend"));
        jsonObject.put("userRealName", request.getSession().getAttribute("userRealName"));

        request.setAttribute("json", jsonObject);

        response.setContentType("application/json;charset=UTF-8");
        ResponseParam(response, JSON.toJSONString(jsonObject));
    }

    @RequestMapping("/ToPay")
    @ResponseBody
    public String ToPay(HttpServletRequest request){
        String ActName = request.getParameter("ActName");
        String AmountPay = request.getParameter("AmountPay");
        String UserName = request.getParameter("UserName");

        try {
            return aliPayService.aliPay(ActName, AmountPay, UserName, request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/CancelEnlist")
    public void CancelEnlist(HttpServletRequest request, HttpServletResponse response){
        String UserId = (String) request.getSession().getAttribute("userId");
        String ActId = request.getParameter("ActId");
        ActUser actUser = new ActUser();
        actUser.setActid(ActId);
        actUser.setUserid(UserId);
        actUserService.deleteById(actUser);

        Activity activity = new Activity();
        activity.setActivityid(ActId);
        activity.setEnlistplayer(activityService.queryById(ActId).getEnlistplayer()-1);
        activityService.update(activity);

        response.setContentType("text/plain");
        try {
            PrintWriter out = response.getWriter();
            out.write("Cancel success!");
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/changeAct")
    @ResponseBody
    public void ChangeAct(@RequestParam("showFile") MultipartFile[] showImg, @RequestParam("file") MultipartFile[] img, HttpServletRequest request, HttpServletResponse response){
        Activity activity = new Activity();
        String activityId = request.getParameter("ActId");
        activity.setActivityid(activityId);

        PrintWriter out = null;
        try {
            response.setContentType("text/plain");
            out = response.getWriter();

            //处理封面图片
            if(showImg.length != 0){
                String showFileName = activityId + "-" + "ASI.jpg";
                // 直接存储到路径中，名字不需要更改
                String newShowFilePath = path + "images/" + showFileName;
                InputStream inputStream = showImg[0].getInputStream();
                File.saveStreamFile(inputStream,newShowFilePath);
            }

            //处理活动展示图片
            if(img.length != 0){
                //删除所有该活动的展示图片（数据库中的），然后重新插入
                imageService.deleteByLikeName(activityId);
                Image image = new Image();
                for (int i = 0; i < img.length; i++) {
                    String FileName = activityId + "-" + i + ".jpg";
                    String newFilePath =path + "images/" + FileName;

                    InputStream inputStream1 = img[i].getInputStream();
                    File.saveStreamFile(inputStream1,newFilePath);

                    image.setImagename(FileName);
                    image.setImageid(GetCode.Get6Code());
                    imageService.insert(image);
                }
            }

            //文章Intro
            String Intro = request.getParameter("ActIntro");
            Article article = new Article();
            article.setArticleintro(Intro);
            article.setArticlename(activityId + "-Des");
            articleService.updateByName(article);

            //处理文本描述
            File.saveTXTFile(request.getParameter("ActDescription"),path + "Des/" + activityId + "-Des.txt");
            File.saveTXTFile(request.getParameter("ActTripDes"),path + "Des/" + activityId + "-TripDes.txt");
            File.saveTXTFile(request.getParameter("ActOutfitDes"),path + "Des/" + activityId + "-OutfitDes.txt");
            File.saveTXTFile(request.getParameter("ActNotice"),path + "Des/" + activityId + "-NoticeDes.txt");

            //类别处理
            String Categories = request.getParameter("Category");
            String[] cateArray = Categories.split("/");

            //类别名存到 list 中
            List list = new ArrayList();
            for(int i = 0; i < cateArray.length; i++){
                list.add(cateArray[i]);
            }
            //类别名存在于数据库的就取出来
            List<Category> tempList = new ArrayList<Category>();
            tempList = categoryService.queryByInNames(list);
            List<ActCate> actcatelist = new ArrayList<ActCate>();

            for(Category temp : tempList){
                if(list.contains(temp.getCatename())){
                    list.remove(temp.getCatename());
                    actcatelist.add(new ActCate(activityId, temp.getCateid()));
                }
            }

            List<Category> catelist = new ArrayList<Category>();

            for (Object temp : list) {
                String cateId = GetCode.Get6Code();
                catelist.add(new Category(cateId, temp.toString()));
                actcatelist.add(new ActCate(activityId, cateId));
            }
            //插入新的类别
            if(!catelist.isEmpty())
                categoryService.insert(catelist);

            //处理其他细则
            activity.setActname(request.getParameter("ActName"));
            activity.setDestination(request.getParameter("Destination"));
            activity.setDepartplace(request.getParameter("DepartPlace"));
            activity.setActdays(Integer.valueOf(request.getParameter("ActDays")));
            activity.setActspend(Double.valueOf(request.getParameter("ActSpend")));
            activity.setVehicle(request.getParameter("Vehicle"));

            Date sql2 = Date.valueOf(request.getParameter("StartTime"));
            System.out.println("test111:" + request.getParameter("StartTime"));
            activity.setStarttime(sql2);

            activity.setExpectplayer(Integer.valueOf(request.getParameter("ExpectPlayer")));

            activityService.update(activity);
            actCateService.deleteById(activityId);
            actCateService.inserts(actcatelist);

            out.write("success");
        }catch (IOException e){
            e.printStackTrace();
            out.write("false");
        }
        out.flush();
        out.close();
    }

    /**
     * 取得本人的置顶活动
     * @param request
     * @param response
     */
    @RequestMapping("/GetTopAct")
    public void GetTopAct(HttpServletRequest request, HttpServletResponse response){
        String Other = request.getParameter("Other");
        String UserId;
        if(Other == null)
            UserId = (String) request.getSession().getAttribute("userId");
        else
            UserId = (String) request.getSession().getAttribute("OtherUserId");

        Users user = new Users();
        user = usersService.queryById(UserId);

        Activity activity = new Activity();
        activity = activityService.queryById(user.getTopactid());
        try {
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter out = response.getWriter();
            System.out.println("Get Top Activity");
            out.write(JSON.toJSONString(activity));
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/GetOtherAct")
    public void GetOtherAct(HttpServletRequest request, HttpServletResponse response){
        String UserId = (String) request.getSession().getAttribute("OtherUserId");

        Users user = new Users();
        user = usersService.queryById(UserId);

        Activity activity = new Activity();
        activity.setOriginatorname(user.getUserrealname());
        List<Activity> activityList = new ArrayList<Activity>();
        activityList = activityService.queryAll(activity);

        Activity temp1 = new Activity();
        for (Activity temp : activityList){
            if(temp.getActivityid().equals(user.getTopactid())){
                temp1 = temp;
                activityList.remove(temp1);
                break;
            }
        }

        Collections.sort(activityList);       //根据日期排序

        try {
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter out = response.getWriter();
            System.out.println("Get Other Activity");
            out.write(JSON.toJSONString(activityList));
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/CheckIsEnlist")
    public void CheckIsEnlist(HttpServletRequest request, HttpServletResponse responses){
        String UserId = (String) request.getSession().getAttribute("userId");
        String ActId = request.getParameter("ActId");

        ActUser actUser = new ActUser();
        actUser.setUserid(UserId);
        actUser.setActid(ActId);

        List<ActUser> list = actUserService.queryAll(actUser);
        responses.setContentType("text/plain");
        if(!list.isEmpty()){
            ResponseParam(responses, "yes");
            System.out.println("ActUser: " + JSON.toJSONString(list));
        }else {
            ResponseParam(responses, "no");
        }
    }

    @RequestMapping("/GetOwnAct")
    public void GetOwnAct(HttpServletRequest request, HttpServletResponse response){
        String UserId = (String) request.getSession().getAttribute("userId");
        Users user = usersService.queryById(UserId);

        Activity activity = new Activity();
        activity.setOriginatorname(user.getUserrealname());
        List<Activity> list = new ArrayList<Activity>();
        list = activityService.queryAll(activity);

        Collections.sort(list);

        response.setContentType("application/json;charset=UTF-8");
        ResponseParam(response, JSON.toJSONString(list));
    }

    @RequestMapping("/DeleteAct")
    public void DeleteAct(HttpServletRequest request, HttpServletResponse response){
        String ActId = request.getParameter("ActId");
        actUserService.deleteByActId(ActId);
        actCateService.deleteById(ActId);
        activityService.deleteById(ActId);

        response.setContentType("text/plain");
        ResponseParam(response, "Success");
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
    public Activity selectOne(String id) {
        return this.activityService.queryById(id);
    }

}