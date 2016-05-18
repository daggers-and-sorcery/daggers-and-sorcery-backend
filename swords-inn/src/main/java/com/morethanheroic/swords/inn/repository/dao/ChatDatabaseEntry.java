package com.morethanheroic.swords.inn.repository.dao;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class ChatDatabaseEntry {

    private int userId;
    private String message;
    private Date writingTime;
}
