package com.morethanheroic.swords.tavern.view.response.quest;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.tavern.view.response.quest.domain.TavernQuestInfoPartialResponse;
import com.morethanheroic.swords.tavern.view.response.quest.domain.TavernQuestInfoPartialResponseBuilderConfiguration;
import org.springframework.stereotype.Service;

@Service
public class TavernQuestInfoPartialResponseBuilder implements PartialResponseBuilder<TavernQuestInfoPartialResponseBuilderConfiguration> {

    @Override
    public PartialResponse build(final TavernQuestInfoPartialResponseBuilderConfiguration tavernQuestInfoPartialResponseBuilderConfiguration) {
        return TavernQuestInfoPartialResponse.builder()
                .shouldShowSmugglersHeavenQuest(tavernQuestInfoPartialResponseBuilderConfiguration.isShouldShowSmugglersHeavenQuest())
                .build();
    }
}
