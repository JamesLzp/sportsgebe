package com.guet.sportsgebe.entity;

import java.io.Serializable;

/**
 * (Image)实体类
 *
 * @author makejava
 * @since 2020-04-10 02:41:53
 */
public class Image implements Serializable {
    private static final long serialVersionUID = 683455565465812941L;
    
    private String imageid;
    
    private String imagename;


    public String getImageid() {
        return imageid;
    }

    public void setImageid(String imageid) {
        this.imageid = imageid;
    }

    public String getImagename() {
        return imagename;
    }

    public void setImagename(String imagename) {
        this.imagename = imagename;
    }

}