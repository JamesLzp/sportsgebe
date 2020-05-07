package com.guet.sportsgebe.entity;

import java.io.Serializable;

/**
 * (ActCate)实体类
 *
 * @author makejava
 * @since 2020-04-21 22:24:31
 */
public class ActCate implements Serializable {
    private static final long serialVersionUID = 331723208797515098L;
    
    private String actid;
    
    private String cateid;

    public ActCate(String actid, String cateid){
        this.actid = actid;
        this.cateid = cateid;
    }

    public String getActid() {
        return actid;
    }

    public void setActid(String actid) {
        this.actid = actid;
    }

    public String getCateid() {
        return cateid;
    }

    public void setCateid(String cateid) {
        this.cateid = cateid;
    }

}