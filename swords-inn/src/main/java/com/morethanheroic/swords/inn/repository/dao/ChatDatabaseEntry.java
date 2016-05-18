package com.morethanheroic.swords.inn.repository.dao;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChatDatabaseEntry {

    private int userId;
    private String message;
    private LocalDateTime writingTime;
}
