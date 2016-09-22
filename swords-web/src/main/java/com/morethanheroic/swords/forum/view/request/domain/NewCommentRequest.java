package com.morethanheroic.swords.forum.view.request.domain;

import lombok.Data;

import java.util.Optional;

/**
 * Created by root on 2016. 08. 23..
 */
@Data
public class NewCommentRequest {

    private boolean isAnswer;
    private String content;
    private int answerToCommentId;
    private int topicId;


}
