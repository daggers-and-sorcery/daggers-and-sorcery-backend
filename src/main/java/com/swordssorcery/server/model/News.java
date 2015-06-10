package com.swordssorcery.server.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class News {

    @Id
    private int id;
    private long timestamp;
    private String title;
    private String message;
    private String icon;

    public int getId() {
        return id;
    }

    public long getTimestamp() {
        return timestamp;
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
}
