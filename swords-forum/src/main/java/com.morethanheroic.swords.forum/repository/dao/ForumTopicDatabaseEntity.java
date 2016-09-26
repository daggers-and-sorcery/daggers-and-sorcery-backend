package com.morethanheroic.swords.forum.repository.dao;

import lombok.Data;

import java.util.Date;

@Data
public class ForumTopicDatabaseEntity {

    private int id;
    private int parentCategory;
    private String name;
    private int commentCount;
    private Date lastPostDate;
    private int lastPostUser;
    private int creator;
}
