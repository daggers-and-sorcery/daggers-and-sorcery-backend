package com.morethanheroic.swords.tavern.view.response.quest.domain;

import com.morethanheroic.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TavernQuestInfoPartialResponse extends PartialResponse {

    private final boolean shouldShowSmugglersHeavenQuest;
}
