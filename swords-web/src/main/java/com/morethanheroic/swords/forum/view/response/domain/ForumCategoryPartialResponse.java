package com.morethanheroic.swords.forum.view.response.domain;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
public class ForumCategoryPartialResponse extends PartialResponse{

    private String name;
    private int postCount;
    private String icon;
    private Date lastPostDate;
    private String lastPoster;
}
