package com.morethanheroic.swords.news.repository.dao;

import java.util.Date;

public class NewsDatabaseEntity {

    private int id;

    private Date release_date = new Date();

    private String title = "";

    private String message = "";

    private String icon = "";

    public int getId() {
        return id;
    }

    public Date getDate() {
        return release_date;
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
        this.release_date = date;
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
