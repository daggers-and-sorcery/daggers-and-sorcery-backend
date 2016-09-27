package com.morethanheroic.swords.forum.repository.dao;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.Instant;

@Getter
@Builder
public class NewTopic {

    private int parentCategory;
    private String name;
    private String content;
    private Instant lastPostDate;
    private int lastPostUser;
    private int creator;
}
