package com.guet.sportsgebe.entity;

import java.io.Serializable;

/**
 * (GeneralAdmin)实体类
 *
 * @author makejava
 * @since 2020-05-08 02:12:15
 */
public class GeneralAdmin implements Serializable {
    private static final long serialVersionUID = 282144292230992059L;
    
    private String gadminid;
    
    private String idcard;
    
    private String gadminname;
    
    private String gadminphone;
    
    private String gadminmail;
    
    private String gadminintro;
    
    private String gadminstate;


    public String getGadminid() {
        return gadminid;
    }

    public void setGadminid(String gadminid) {
        this.gadminid = gadminid;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getGadminname() {
        return gadminname;
    }

    public void setGadminname(String gadminname) {
        this.gadminname = gadminname;
    }

    public String getGadminphone() {
        return gadminphone;
    }

    public void setGadminphone(String gadminphone) {
        this.gadminphone = gadminphone;
    }

    public String getGadminmail() {
        return gadminmail;
    }

    public void setGadminmail(String gadminmail) {
        this.gadminmail = gadminmail;
    }

    public String getGadminintro() {
        return gadminintro;
    }

    public void setGadminintro(String gadminintro) {
        this.gadminintro = gadminintro;
    }

    public String getGadminstate() {
        return gadminstate;
    }

    public void setGadminstate(String gadminstate) {
        this.gadminstate = gadminstate;
    }

}