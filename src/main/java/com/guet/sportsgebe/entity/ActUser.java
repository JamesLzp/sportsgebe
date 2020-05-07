package com.guet.sportsgebe.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (ActUser)实体类
 *
 * @author makejava
 * @since 2020-04-27 22:58:00
 */
public class ActUser implements Serializable {
    private static final long serialVersionUID = -48142751878352368L;
    
    private String userid;
    
    private String actid;
    
    private Date enlisttime;
    
    private Double amountpaid;
    
    private String attendstate;
    
    private String isleader;


    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getActid() {
        return actid;
    }

    public void setActid(String actid) {
        this.actid = actid;
    }

    public Date getEnlisttime() {
        return enlisttime;
    }

    public void setEnlisttime(Date enlisttime) {
        this.enlisttime = enlisttime;
    }

    public Double getAmountpaid() {
        return amountpaid;
    }

    public void setAmountpaid(Double amountpaid) {
        this.amountpaid = amountpaid;
    }

    public String getAttendstate() {
        return attendstate;
    }

    public void setAttendstate(String attendstate) {
        this.attendstate = attendstate;
    }

    public String getIsleader() {
        return isleader;
    }

    public void setIsleader(String isleader) {
        this.isleader = isleader;
    }

}