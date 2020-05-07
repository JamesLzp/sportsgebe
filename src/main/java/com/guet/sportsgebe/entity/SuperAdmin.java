package com.guet.sportsgebe.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (SuperAdmin)实体类
 *
 * @author makejava
 * @since 2020-05-07 22:15:11
 */
public class SuperAdmin implements Serializable {
    private static final long serialVersionUID = -40467930379598376L;
    
    private String sadminid;
    
    private String sadminname;
    
    private String sapassword;
    
    private Date lastlogintime;


    public String getSadminid() {
        return sadminid;
    }

    public void setSadminid(String sadminid) {
        this.sadminid = sadminid;
    }

    public String getSadminname() {
        return sadminname;
    }

    public void setSadminname(String sadminname) {
        this.sadminname = sadminname;
    }

    public String getSapassword() {
        return sapassword;
    }

    public void setSapassword(String sapassword) {
        this.sapassword = sapassword;
    }

    public Date getLastlogintime() {
        return lastlogintime;
    }

    public void setLastlogintime(Date lastlogintime) {
        this.lastlogintime = lastlogintime;
    }

}