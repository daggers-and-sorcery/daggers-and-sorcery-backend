package com.morethanheroic.swords.news.repository.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "news")
public class NewsDatabaseEntity {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private Date date = new Date();

    @Column
    private String title = "";

    @Column
    private String message = "";

    @Column
    private String icon = "";

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public String getIcon() {
        return icon;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
