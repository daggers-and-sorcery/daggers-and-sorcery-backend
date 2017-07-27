package com.morethanheroic.swords.explore.view.response;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.explore.view.response.domain.EventInfoPartialResponse;
import com.morethanheroic.swords.explore.view.response.domain.ExplorationResponseBuilderConfiguration;
import org.springframework.stereotype.Service;

@Service
public class ExplorationEventInfoPartialResponseBuilder implements PartialResponseBuilder<ExplorationResponseBuilderConfiguration> {

    @Override
    public PartialResponse build(ExplorationResponseBuilderConfiguration explorationResponseBuilderConfiguration) {
        return EventInfoPartialResponse.builder()
                .name(explorationResponseBuilderConfiguration.getExplorationEventEntryResults().getName())
                .terrain(explorationResponseBuilderConfiguration.getExplorationEventEntryResults().getTerrain())
                .rarity(explorationResponseBuilderConfiguration.getExplorationEventEntryResults().getRarity())
                .build();
    }
}
