package com.guet.sportsgebe.entity;

import java.io.Serializable;

/**
 * (Category)实体类
 *
 * @author makejava
 * @since 2020-04-21 23:27:36
 */
public class Category implements Serializable {
    private static final long serialVersionUID = -15087782143907727L;

    private String cateid;

    private String catename;

    public Category(String cateid, String catename){
        this.cateid = cateid;
        this.catename = catename;
    }

    public String getCateid() {
        return cateid;
    }

    public void setCateid(String cateid) {
        this.cateid = cateid;
    }

    public String getCatename() {
        return catename;
    }

    public void setCatename(String catename) {
        this.catename = catename;
    }

}