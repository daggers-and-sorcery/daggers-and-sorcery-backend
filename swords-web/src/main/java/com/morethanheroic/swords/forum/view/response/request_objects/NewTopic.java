package com.morethanheroic.swords.forum.view.response.request_objects;

import lombok.Data;

/**
 * Created by root on 2016. 08. 08..
 */
@Data
public class NewTopic {

    private int categoryId;
    private String name;
    private String content;

}
