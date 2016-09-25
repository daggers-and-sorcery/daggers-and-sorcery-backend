package com.morethanheroic.swords.forum.view.response.domain.category;

import com.morethanheroic.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
public class ForumCategoryPartialResponse extends PartialResponse{

    private int id;
    private String name;
    private int postCount;
    private String icon;
    private Date lastPostDate;
    private String lastPoster;
}
