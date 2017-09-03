package com.morethanheroic.swords.view.response.quest.domain;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TavernQuestInfoPartialResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final boolean shouldShowSmugglersHeavenQuest;
}
