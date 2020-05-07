package com.guet.sportsgebe.Act;

import com.alibaba.fastjson.JSON;
import com.guet.sportsgebe.entity.ActUser;
import com.guet.sportsgebe.service.ActUserService;
import com.guet.sportsgebe.service.ActivityService;
import com.guet.sportsgebe.util.File;
import com.guet.sportsgebe.util.GetCode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class Activity {

    @Resource
    private ActivityService activityService;
    @Resource
    private ActUserService actUserService;

    @Value("${web.upload-path}")
    private String path;

    @Test
    void GetAllDesTest(){
        List<com.guet.sportsgebe.entity.Activity> ActList = new ArrayList<com.guet.sportsgebe.entity.Activity>();

        com.guet.sportsgebe.entity.Activity act = new com.guet.sportsgebe.entity.Activity();
        act.setDestination("山");
        ActList = activityService.queryByLike(act);
        System.out.println(JSON.toJSONString(ActList));
    }

    @Test
    void saveTXT(){
        String articleId = GetCode.Get6Code();
        String str = "hdsuahdu<br/>hdushfdhfdj<br/>dhushddhi";

        File.saveTXTFile(str,path + "Des/" + articleId + "-Des.txt");
        File.saveTXTFile(str,path + "Des/" + articleId + "-TripDes.txt");
        File.saveTXTFile(str,path + "Des/" + articleId + "-OutfitDes.txt");
        File.saveTXTFile(str,path + "Des/" + articleId + "-NoticeDes.txt");
    }

    @Test
    void changeDate(){
        Date sql2 = Date.valueOf("2020-04-01");
        System.out.println(sql2);
    }

    @Test
    void ActOrderByTest(){
        List<com.guet.sportsgebe.entity.Activity> list = new ArrayList<com.guet.sportsgebe.entity.Activity>();
        list = activityService.queryAllByLimit(0, 4);
        for (com.guet.sportsgebe.entity.Activity temp : list){
            System.out.println(temp.getStarttime());
        }
    }

    @Test
    void TestActUser(){
        String ActId = "1986D4";
        String UserId = "UI823";

        ActUser actUser = new ActUser();
        actUser.setUserid(UserId);
        actUser.setActid(ActId);
        actUserService.deleteById(actUser);
    }
}
