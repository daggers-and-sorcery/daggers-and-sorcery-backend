package com.morethanheroic.swords.ladder.view.response.domain;

import com.morethanheroic.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LadderInfoPartialResponse extends PartialResponse {

    private String username;
    private int level;
    private int xp;
    private boolean isMe;
}
