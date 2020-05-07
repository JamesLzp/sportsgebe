package com.guet.sportsgebe.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Article)实体类
 *
 * @author makejava
 * @since 2020-04-19 19:53:52
 */
public class Article implements Comparable<Article> {
    
    private String articleid;
    
    private String articlename;
    
    private Date publishedtime;
    
    private String articlecate;
    
    private String articleintro;
    
    private String ishotarticle;


    public String getArticleid() {
        return articleid;
    }

    public void setArticleid(String articleid) {
        this.articleid = articleid;
    }

    public String getArticlename() {
        return articlename;
    }

    public void setArticlename(String articlename) {
        this.articlename = articlename;
    }

    public Date getPublishedtime() {
        return publishedtime;
    }

    public void setPublishedtime(Date publishedtime) {
        this.publishedtime = publishedtime;
    }

    public String getArticlecate() {
        return articlecate;
    }

    public void setArticlecate(String articlecate) {
        this.articlecate = articlecate;
    }

    public String getArticleintro() {
        return articleintro;
    }

    public void setArticleintro(String articleintro) {
        this.articleintro = articleintro;
    }

    public String getIshotarticle() {
        return ishotarticle;
    }

    public void setIshotarticle(String ishotarticle) {
        this.ishotarticle = ishotarticle;
    }

    @Override
    public int compareTo(Article o) {
        int flag = this.getPublishedtime().compareTo(o.publishedtime);

        return flag;
    }

}