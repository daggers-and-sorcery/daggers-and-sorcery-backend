package com.morethanheroic.swords.forum.repository.dao;

import lombok.Builder;
import lombok.Getter;

import java.util.Optional;

/**
 * Created by root on 2016. 08. 23..
 */

@Getter
@Builder
public class NewComment {

    private boolean isAnswer;
    private String content;
    private int answerToCommentId;
    private int topicId;

}
