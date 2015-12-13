package com.morethanheroic.swords.news.repository.dao;

import lombok.Getter;

import java.time.LocalDate;

/**
 * Contains the data of a news entry in the database.
 */
@Getter
public class NewsDatabaseEntity {

    private int id;
    private LocalDate releaseDate;
    private String title;
    private String message;
    private String icon;
}
