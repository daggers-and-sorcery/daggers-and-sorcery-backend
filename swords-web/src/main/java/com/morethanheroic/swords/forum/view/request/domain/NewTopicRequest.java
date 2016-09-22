package com.morethanheroic.swords.forum.view.request.domain;

import lombok.Data;

import java.time.Instant;

/**
 * Created by root on 2016. 08. 08..
 */
@Data
public class NewTopicRequest {

    private int parentCategory;
    private String name;
    private String content;


}
