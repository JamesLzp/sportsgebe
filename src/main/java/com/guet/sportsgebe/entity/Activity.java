package com.guet.sportsgebe.entity;

import java.sql.Date;
import java.io.Serializable;

/**
 * (Activity)实体类
 *
 * @author makejava
 * @since 2020-04-12 00:45:08
 */
public class Activity implements Comparable<Activity> {
    
    private String activityid;
    
    private String originatorname;
    
    private String actname;
    
    private String destination;
    
    private String departplace;
    
    private Date starttime;
    
    private Integer actdays;
    
    private String vehicle;
    
    private Double actspend;
    
    private String ishotlist;
    
    private String actstate;
    
    private Integer expectplayer;
    
    private Integer enlistplayer;
    
    private Integer hits;
    
    private Integer respond;
    
    private String ActShowImgName;
    
    private String actdescriptionid;
    
    private String acttripdesid;
    
    private String actoutfitdesid;
    
    private String actnoticeid;


    public String getActivityid() {
        return activityid;
    }

    public void setActivityid(String activityid) {
        this.activityid = activityid;
    }

    public String getOriginatorname() {
        return originatorname;
    }

    public void setOriginatorname(String originatorname) {
        this.originatorname = originatorname;
    }

    public String getActname() {
        return actname;
    }

    public void setActname(String actname) {
        this.actname = actname;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDepartplace() {
        return departplace;
    }

    public void setDepartplace(String departplace) {
        this.departplace = departplace;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Integer getActdays() {
        return actdays;
    }

    public void setActdays(Integer actdays) {
        this.actdays = actdays;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public Double getActspend() {
        return actspend;
    }

    public void setActspend(Double actspend) {
        this.actspend = actspend;
    }

    public String getIshotlist() {
        return ishotlist;
    }

    public void setIshotlist(String ishotlist) {
        this.ishotlist = ishotlist;
    }

    public String getActstate() {
        return actstate;
    }

    public void setActstate(String actstate) {
        this.actstate = actstate;
    }

    public Integer getExpectplayer() {
        return expectplayer;
    }

    public void setExpectplayer(Integer expectplayer) {
        this.expectplayer = expectplayer;
    }

    public Integer getEnlistplayer() {
        return enlistplayer;
    }

    public void setEnlistplayer(Integer enlistplayer) {
        this.enlistplayer = enlistplayer;
    }

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    public Integer getRespond() {
        return respond;
    }

    public void setRespond(Integer respond) {
        this.respond = respond;
    }

    public String getActShowImgName() {
        return ActShowImgName;
    }

    public void setActShowImgName(String ActShowImgName) {
        this.ActShowImgName = ActShowImgName;
    }

    public String getActdescriptionid() {
        return actdescriptionid;
    }

    public void setActdescriptionid(String actdescriptionid) {
        this.actdescriptionid = actdescriptionid;
    }

    public String getActtripdesid() {
        return acttripdesid;
    }

    public void setActtripdesid(String acttripdesid) {
        this.acttripdesid = acttripdesid;
    }

    public String getActoutfitdesid() {
        return actoutfitdesid;
    }

    public void setActoutfitdesid(String actoutfitdesid) {
        this.actoutfitdesid = actoutfitdesid;
    }

    public String getActnoticeid() {
        return actnoticeid;
    }

    public void setActnoticeid(String actnoticeid) {
        this.actnoticeid = actnoticeid;
    }

    @Override
    public int compareTo(Activity o) {
        int flag = this.starttime.compareTo(o.starttime);

        return flag;
    }
}