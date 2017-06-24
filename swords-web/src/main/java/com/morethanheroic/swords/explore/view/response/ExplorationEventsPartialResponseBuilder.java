package com.morethanheroic.swords.explore.view.response;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.combat.view.response.service.CombatAttackPartialResponseCollectionBuilder;
import com.morethanheroic.swords.combat.view.response.service.CombatAttackStatusPartialResponseBuilder;
import com.morethanheroic.swords.combat.view.response.service.domain.CombatAttackPartialResponseCollectionBuilderConfiguration;
import com.morethanheroic.swords.combat.view.response.service.domain.CombatAttackStatusPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.explore.domain.event.result.ExplorationEventEntryResult;
import com.morethanheroic.swords.explore.domain.event.result.impl.*;
import com.morethanheroic.swords.explore.view.response.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExplorationEventsPartialResponseBuilder implements PartialResponseCollectionBuilder<ExplorationResponseBuilderConfiguration> {

    private final CombatAttackPartialResponseCollectionBuilder combatAttackPartialResponseCollectionBuilder;
    private final CombatAttackStatusPartialResponseBuilder combatAttackStatusPartialResponseBuilder;

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
            } else if (explorationEventEntryResult instanceof MessageBoxExplorationEventEntryResult) {
                final MessageBoxExplorationEventEntryResult messageBoxExplorationEventEntryResult = (MessageBoxExplorationEventEntryResult) explorationEventEntryResult;

                result.add(
                        MessageBoxExplorationEventPartialResponse.builder()
                                .content(messageBoxExplorationEventEntryResult.getContent())
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
                                .status(
                                        combatAttackStatusPartialResponseBuilder.build(
                                                CombatAttackStatusPartialResponseBuilderConfiguration.builder()
                                                        .combatEnded(combatExplorationEventEntryResult.isCombatEnded())
                                                        .playerDead(combatExplorationEventEntryResult.isPlayerDead())
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
            } else if (explorationEventEntryResult instanceof ContinueQuestExplorationEventEntryResult) {
                final ContinueQuestExplorationEventEntryResult questExplorationEventEntryResult = (ContinueQuestExplorationEventEntryResult) explorationEventEntryResult;

                result.add(
                        ContinueQuestExplorationEventPartialResponse.builder()
                                .questId(questExplorationEventEntryResult.getQuestId())
                                .build()
                );
            } else if (explorationEventEntryResult instanceof AttributeExplorationEventEntryResult) {
                final AttributeExplorationEventEntryResult attributeExplorationEventEntryResult = (AttributeExplorationEventEntryResult) explorationEventEntryResult;

                result.add(
                        AttributeAttemptExplorationEventPartialResponse.builder()
                                .messages(
                                        attributeExplorationEventEntryResult.getResult().stream()
                                                .map(
                                                        (textExplorationEventEntryResult) -> TextExplorationEventPartialResponse.builder()
                                                                .content(textExplorationEventEntryResult.getContent())
                                                                .build()
                                                )
                                                .collect(Collectors.toList())
                                )
                                .build()
                );
            } else if (explorationEventEntryResult instanceof QuestExplorationEventEntryResult) {
                final QuestExplorationEventEntryResult questExplorationEventEntryResult = (QuestExplorationEventEntryResult) explorationEventEntryResult;

                result.add(
                        QuestExplorationEventPartialResponse.builder()
                                .name(questExplorationEventEntryResult.getName())
                                .description(questExplorationEventEntryResult.getDescription())
                                .acceptQuestStage(questExplorationEventEntryResult.getAcceptQuestStage())
                                .declineQuestStage(questExplorationEventEntryResult.getDeclineQuestStage())
                                .build()
                );
            }
        }

        return result;
    }
}
