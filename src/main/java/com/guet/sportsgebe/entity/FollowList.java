package com.guet.sportsgebe.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (FollowList)实体类
 *
 * @author makejava
 * @since 2020-04-21 14:39:07
 */
public class FollowList implements Serializable {
    private static final long serialVersionUID = 196674955126321837L;
    
    private String userid;
    
    private String followerid;
    
    private Date followertime;


    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getFollowerid() {
        return followerid;
    }

    public void setFollowerid(String followerid) {
        this.followerid = followerid;
    }

    public Date getFollowertime() {
        return followertime;
    }

    public void setFollowertime(Date followertime) {
        this.followertime = followertime;
    }

}