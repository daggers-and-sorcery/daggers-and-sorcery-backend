package com.morethanheroic.swords.news.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

/**
 * Contains the data of a news entry.
 */
@Builder
@Getter
public class NewsEntity {

    private final int id;
    private final long releaseDate;
    private final String title;
    private final String message;
    private final String icon;
}
