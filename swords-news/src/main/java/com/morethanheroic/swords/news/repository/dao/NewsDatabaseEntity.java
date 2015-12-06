package com.morethanheroic.swords.news.repository.dao;

import lombok.Getter;

import java.util.Date;

@Getter
public class NewsDatabaseEntity {

    private int id;
    private Date releaseDate;
    private String title;
    private String message;
    private String icon;
}
