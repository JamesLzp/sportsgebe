package com.guet.sportsgebe.entity;

import java.io.Serializable;

/**
 * (Users)实体类
 *
 * @author makejava
 * @since 2020-03-15 01:52:10
 */
public class Users implements Serializable {
    private static final long serialVersionUID = -33901109210314681L;
    
    private String userid;
    
    private String username;
    
    private String userphotoid;
    
    private String userrealname;
    
    private String userpassword;
    
    private String userphone;
    
    private String usermail;
    
    private String isadmin;
    
    private String topactid;
    
    private String toparticleid;


    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserphotoid() {
        return userphotoid;
    }

    public void setUserphotoid(String userphotoid) {
        this.userphotoid = userphotoid;
    }

    public String getUserrealname() {
        return userrealname;
    }

    public void setUserrealname(String userrealname) {
        this.userrealname = userrealname;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    public String getUsermail() {
        return usermail;
    }

    public void setUsermail(String usermail) {
        this.usermail = usermail;
    }

    public String getIsadmin() {
        return isadmin;
    }

    public void setIsadmin(String isadmin) {
        this.isadmin = isadmin;
    }

    public String getTopactid() {
        return topactid;
    }

    public void setTopactid(String topactid) {
        this.topactid = topactid;
    }

    public String getToparticleid() {
        return toparticleid;
    }

    public void setToparticleid(String toparticleid) {
        this.toparticleid = toparticleid;
    }

}