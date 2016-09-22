package com.morethanheroic.swords.forum.repository.dao;

import lombok.Builder;
import lombok.Getter;

import java.util.Optional;

@Getter
@Builder
public class NewComment {

    private boolean isAnswer;
    private String content;
    private int answerToCommentId;
    private int topicId;
}
