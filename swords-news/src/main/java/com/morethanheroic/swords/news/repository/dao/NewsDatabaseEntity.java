package com.morethanheroic.swords.news.repository.dao;

import lombok.Getter;

import java.util.Date;

/**
 * Contains the data of a news entry in the database.
 */
@Getter
public class NewsDatabaseEntity {

    private int id;
    private Date releaseDate;
    private String title;
    private String message;
    private String icon;
}
