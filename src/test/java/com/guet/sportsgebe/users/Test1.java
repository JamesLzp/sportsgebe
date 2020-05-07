package com.guet.sportsgebe.users;

import com.alibaba.fastjson.JSON;
import com.guet.sportsgebe.entity.Activity;
import com.guet.sportsgebe.entity.FollowList;
import com.guet.sportsgebe.entity.Users;
import com.guet.sportsgebe.service.ActivityService;
import com.guet.sportsgebe.service.FollowListService;
import com.guet.sportsgebe.service.UsersService;
import com.guet.sportsgebe.util.GetCode;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.util.comparator.ComparableComparator;

import javax.annotation.Resource;
import java.util.*;

@SpringBootTest
public class Test1 {

    @Resource
    private FollowListService followListService;
    @Resource
    private UsersService usersService;
    @Resource
    private ActivityService activityService;

    @Test
    public void getCode(){
        System.out.println(GetCode.Get6Code());
    }

    @Test
    void GetUserName(){
        String UserId = "8332B";
        List<FollowList> followLists = new ArrayList<FollowList>();
        followLists = followListService.queryById(UserId);
        List tempList = new ArrayList();
        for (FollowList temp : followLists){
            System.out.println(temp.getFollowerid());
            tempList.add(temp.getFollowerid());
        }

        List<Users> usersList = new ArrayList<Users>();
        usersList = usersService.queryByInIds(tempList);
        System.out.println(JSON.toJSONString(usersList));

        List tempNameList = new ArrayList();
        for (Users users : usersList){
            tempNameList.add(users.getUserrealname());
        }
        tempNameList.add("梁志鹏");
        System.out.println(JSON.toJSONString(tempNameList));

        List<Activity> activities = new ArrayList<Activity>();
        activities = activityService.queryByInIds(tempNameList);
        for (Activity temp : activities){
            System.out.println(temp.getStarttime());
        }

        Collections.sort(activities);
        for (Activity temp : activities){
            System.out.println(temp.getStarttime());
        }
    }

    @Test
    void GetUserByRealName(){
        Users user = new Users();
        user.setUserrealname("梁志鹏");
        List<Users> usersList = new ArrayList<Users>();
        usersList = usersService.queryAll(user);
        for (Users u : usersList){
            System.out.println(u.getUserrealname());
        }
    }

    @Test
    void GetUserById(){
        Users user = new Users();
        user = usersService.queryById("88888");
        System.out.println(JSON.toJSONString(user));
    }

    @Test
    void UpdateTop(){
        Users user = new Users();

        user.setUserid("8332B");
        user.setToparticleid("");
        user = usersService.updateTop(user);
        System.out.println("updateTop: " + JSON.toJSONString(user));
    }
}
