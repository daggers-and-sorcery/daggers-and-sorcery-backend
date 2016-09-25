package com.morethanheroic.swords.forum.repository.dao;

import lombok.Data;

import java.util.Date;

@Data
public class ForumCategoriesDatabaseEntity {

    private int id;
    private String name;
    private int postCount;
    private String icon;
    private Date lastPostDate;
    private int lastPostUser;
}
