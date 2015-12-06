package com.morethanheroic.swords.news.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
public class NewsEntity {

    private final int id;
    private final Date releaseDate;
    private final String title;
    private final String message;
    private final String icon;
}
