package com.swordssorcery.server.model.db.news;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "news")
public class NewsDatabaseEntity {

    @Id
    private int id;
    private Date date = new Date();
    private String title = "";
    private String message = "";
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
