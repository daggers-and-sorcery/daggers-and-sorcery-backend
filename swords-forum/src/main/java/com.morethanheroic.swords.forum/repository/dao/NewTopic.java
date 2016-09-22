package com.morethanheroic.swords.forum.repository.dao;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.Instant;

/**
 * Created by root on 2016. 08. 08..
 */
@Getter
@Builder
public class NewTopic {

    private int parentCategory;
    private String name;
    private String content;
    private int commentCount;
    private Instant lastPostDate;
    private int lastPostUser;
    private int creator;


}
