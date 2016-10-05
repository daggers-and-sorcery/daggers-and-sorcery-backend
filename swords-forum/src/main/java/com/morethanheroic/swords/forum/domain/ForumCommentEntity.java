package com.morethanheroic.swords.forum.domain;

import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
public class ForumCommentEntity {

    private final int id;
    private final String content;
    private final long postDate;
    private final UserEntity postUser;
    private final ForumCommentEntity answerToComment;

    public boolean isAnswer() {
        return answerToComment != null;
    }
}
