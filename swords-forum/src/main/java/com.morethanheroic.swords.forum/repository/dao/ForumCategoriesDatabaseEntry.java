package com.morethanheroic.swords.forum.repository.dao;

import lombok.Data;

/**
 * Created by root on 2016.07.16..
 */

@Data
public class ForumCategoriesDatabaseEntry {

    private String name;
    private int postCount;
    private String icon;
    private String lastPostDate;
    private int lastPostUser;
    /*
    * name (varchar [256])
post_count (mediumint unsigned)
icon (varchar [64])
last_post_date (datetime)
last_post_user (mediumint unsigned)*/
}
