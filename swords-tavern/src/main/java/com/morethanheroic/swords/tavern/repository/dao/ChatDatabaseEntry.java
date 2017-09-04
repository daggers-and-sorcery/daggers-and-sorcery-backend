package com.morethanheroic.swords.tavern.repository.dao;

import lombok.Data;

import java.util.Date;

@Data
public class ChatDatabaseEntry {

    private int userId;
    private String message;
    private Date writingTime;
}
