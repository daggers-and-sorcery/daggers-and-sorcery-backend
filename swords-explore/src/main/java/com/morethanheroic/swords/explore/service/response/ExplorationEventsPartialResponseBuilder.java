package com.morethanheroic.swords.explore.service.response;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.explore.domain.event.result.ExplorationEventEntryResult;
import com.morethanheroic.swords.explore.domain.event.result.impl.CombatExplorationEventEntryResult;
import com.morethanheroic.swords.explore.domain.event.result.impl.OptionExplorationEventEntryResult;
import com.morethanheroic.swords.explore.domain.event.result.impl.TextExplorationEventEntryResult;
import com.morethanheroic.swords.explore.service.response.domain.CombatExplorationEventPartialResponse;
import com.morethanheroic.swords.explore.service.response.domain.ExplorationResponseBuilderConfiguration;
import com.morethanheroic.swords.explore.service.response.domain.OptionExplorationEventPartialResponse;
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
            //TODO: Refactor this!
            if (explorationEventEntryResult instanceof TextExplorationEventEntryResult) {
                final TextExplorationEventEntryResult textExplorationEventEntryResult = (TextExplorationEventEntryResult) explorationEventEntryResult;

                result.add(
                        TextExplorationEventPartialResponse.builder()
                                .content(textExplorationEventEntryResult.getContent())
                                .build()
                );
            } else if (explorationEventEntryResult instanceof CombatExplorationEventEntryResult) {
                final CombatExplorationEventEntryResult combatExplorationEventEntryResult = (CombatExplorationEventEntryResult) explorationEventEntryResult;

                result.add(
                        CombatExplorationEventPartialResponse.builder()
                                .combatMessages(combatExplorationEventEntryResult.getCombatMessages())
                                .build()
                );
            } else if (explorationEventEntryResult instanceof OptionExplorationEventEntryResult) {
                final OptionExplorationEventEntryResult optionExplorationEventEntryResult = (OptionExplorationEventEntryResult) explorationEventEntryResult;

                result.add(
                        OptionExplorationEventPartialResponse.builder()
                                .eventOptions(optionExplorationEventEntryResult.getOptions())
                                .build()
                );
            }
        }

        return result;
    }
}
