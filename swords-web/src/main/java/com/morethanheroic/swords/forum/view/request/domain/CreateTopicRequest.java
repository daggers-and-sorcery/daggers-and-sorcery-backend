package com.morethanheroic.swords.forum.view.request.domain;

import lombok.Data;

import java.time.Instant;

@Data
public class CreateTopicRequest {

    private int parentCategory;
    private String name;
    private String content;
}
