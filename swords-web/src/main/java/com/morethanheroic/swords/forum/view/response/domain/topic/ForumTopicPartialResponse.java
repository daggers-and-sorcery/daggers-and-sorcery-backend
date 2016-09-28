package com.morethanheroic.swords.forum.view.response.domain.topic;

import com.morethanheroic.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ForumTopicPartialResponse extends PartialResponse {

    private final int id;
    private final String parentName;
    private final String name;
    private final int commentCount;
    private final long lastPostDate;
    private final String lastPoster;
    private final String creator;
}
