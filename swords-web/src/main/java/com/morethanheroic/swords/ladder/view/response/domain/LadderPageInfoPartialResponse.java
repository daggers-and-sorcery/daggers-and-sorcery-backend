package com.morethanheroic.swords.ladder.view.response.domain;

import com.morethanheroic.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LadderPageInfoPartialResponse extends PartialResponse {

    private int pageCount;
}
