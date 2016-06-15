package com.morethanheroic.swords.ladder.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LadderEntry {

    private String username;
    private int level;
    private int xp;
    private boolean isMe;
}
