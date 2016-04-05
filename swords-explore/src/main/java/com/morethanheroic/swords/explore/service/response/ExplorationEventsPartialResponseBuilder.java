package com.morethanheroic.swords.explore.service.response;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.explore.domain.event.result.ExplorationEventEntryResult;
import com.morethanheroic.swords.explore.domain.event.result.impl.CombatExplorationEventEntryResult;
import com.morethanheroic.swords.explore.domain.event.result.impl.TextExplorationEventEntryResult;
import com.morethanheroic.swords.explore.service.response.domain.CombatExplorationEventPartialResponse;
import com.morethanheroic.swords.explore.service.response.domain.ExplorationResponseBuilderConfiguration;
import com.morethanheroic.swords.explore.service.response.domain.TextExplorationEventPartialResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ExplorationEventsPartialResponseBuilder implements PartialResponseCollectionBuilder<ExplorationResponseBuilderConfiguration> {

    @Override
    public Collection<? extends PartialResponse> build(ExplorationResponseBuilderConfiguration explorationResponseBuilderConfiguration) {
        final List<PartialResponse> result = new ArrayList<>();

        for (ExplorationEventEntryResult explorationEventEntryResult : explorationResponseBuilderConfiguration.getExplorationEventEntryResults().getExplorationEventEntryResults()) {
            if (explorationEventEntryResult instanceof TextExplorationEventEntryResult) {
                result.add(
                        TextExplorationEventPartialResponse.builder()
                                .build()
                );
            } else if (explorationEventEntryResult instanceof CombatExplorationEventEntryResult) {
                result.add(
                        CombatExplorationEventPartialResponse.builder()
                                .build()
                );
            }
        }

        return result;
    }
}
