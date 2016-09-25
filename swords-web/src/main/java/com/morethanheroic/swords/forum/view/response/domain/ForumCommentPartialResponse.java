package com.morethanheroic.swords.forum.view.response.domain;

import com.morethanheroic.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
public class ForumCommentPartialResponse extends PartialResponse {

    private String content;
    private Date postDate;
    private String userName;
    private int answerToCommentId;
}
