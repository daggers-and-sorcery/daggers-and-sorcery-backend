package com.morethanheroic.swords.forum.repository.dao;

import lombok.Data;

import java.util.Date;

@Data
public class ForumCommentDatabaseEntity {

    private int id;
    private int topicId;
    private String content;
    private Date postDate;
    private int postUser;
    private boolean isAnswer;
    private int answerToComment;
}
