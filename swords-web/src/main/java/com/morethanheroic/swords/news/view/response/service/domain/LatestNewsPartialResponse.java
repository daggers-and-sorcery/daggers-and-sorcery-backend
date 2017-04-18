package com.morethanheroic.swords.news.view.response.service.domain;

import com.morethanheroic.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LatestNewsPartialResponse extends PartialResponse {

    private final long releaseDate;
    private final String title;
    private final String message;
    private final String icon;
}
