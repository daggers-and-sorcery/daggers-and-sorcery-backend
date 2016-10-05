package com.morethanheroic.swords.forum.view.request.domain;

import lombok.Data;

import java.util.Optional;

@Data
public class CreateCommentRequest {

    private boolean isAnswer;
    private String content;
    private int answerToCommentId;
    private int parentTopic;
}
