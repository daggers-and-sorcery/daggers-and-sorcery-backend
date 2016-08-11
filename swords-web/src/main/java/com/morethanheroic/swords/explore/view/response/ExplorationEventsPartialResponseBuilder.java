package com.morethanheroic.swords.explore.view.response;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.combat.view.response.service.CombatAttackPartialResponseCollectionBuilder;
import com.morethanheroic.swords.combat.view.response.service.domain.CombatAttackPartialResponseCollectionBuilderConfiguration;
import com.morethanheroic.swords.explore.domain.event.result.ExplorationEventEntryResult;
import com.morethanheroic.swords.explore.domain.event.result.impl.CombatExplorationEventEntryResult;
import com.morethanheroic.swords.explore.domain.event.result.impl.OptionExplorationEventEntryResult;
import com.morethanheroic.swords.explore.domain.event.result.impl.TextExplorationEventEntryResult;
import com.morethanheroic.swords.explore.view.response.domain.CombatExplorationEventPartialResponse;
import com.morethanheroic.swords.explore.view.response.domain.ExplorationResponseBuilderConfiguration;
import com.morethanheroic.swords.explore.view.response.domain.OptionExplorationEventPartialResponse;
import com.morethanheroic.swords.explore.view.response.domain.TextExplorationEventPartialResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExplorationEventsPartialResponseBuilder implements PartialResponseCollectionBuilder<ExplorationResponseBuilderConfiguration> {

    private final CombatAttackPartialResponseCollectionBuilder combatAttackPartialResponseCollectionBuilder;

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
                                .combatMessages(
                                        combatAttackPartialResponseCollectionBuilder.build(
                                                CombatAttackPartialResponseCollectionBuilderConfiguration.builder()
                                                        .combatSteps(combatExplorationEventEntryResult.getCombatSteps())
                                                        .build()
                                        )
                                )
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
