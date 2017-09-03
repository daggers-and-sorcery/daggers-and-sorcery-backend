package com.morethanheroic.swords.view.response.quest;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.view.response.quest.domain.TavernQuestInfoPartialResponse;
import com.morethanheroic.swords.view.response.quest.domain.TavernQuestInfoPartialResponseBuilderConfiguration;

public class TavernQuestInfoPartialResponseBuilder implements PartialResponseBuilder<TavernQuestInfoPartialResponseBuilderConfiguration> {

    @Override
    public PartialResponse build(final TavernQuestInfoPartialResponseBuilderConfiguration tavernQuestInfoPartialResponseBuilderConfiguration) {
        return TavernQuestInfoPartialResponse.builder()
                .shouldShowSmugglersHeavenQuest(tavernQuestInfoPartialResponseBuilderConfiguration.isShouldShowSmugglersHeavenQuest())
                .build();
    }
}
