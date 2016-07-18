package com.morethanheroic.swords.explore.service.event.impl.sevgard.whisperingwoods;

import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.service.event.ExplorationEvent;
import com.morethanheroic.swords.explore.service.event.ExplorationEventDefinition;
import com.morethanheroic.swords.explore.service.event.ExplorationEventLocationType;
import com.morethanheroic.swords.explore.service.event.ExplorationResultFactory;
import com.morethanheroic.swords.explore.service.event.evaluator.MessageEventEntryEvaluator;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;

@ExplorationEvent
public class WoodcuttersExplorationEventDefinition extends ExplorationEventDefinition {

    @Autowired
    private ExplorationResultFactory explorationResultFactory;

    @Autowired
    private MessageEventEntryEvaluator messageEventEntryEvaluator;

    @Override
    public int getId() {
        return 12;
    }

    @Override
    public ExplorationEventLocationType getLocation() {
        return ExplorationEventLocationType.WHISPERING_WOODS;
    }

    @Override
    public ExplorationResult explore(UserEntity userEntity) {
        final ExplorationResult explorationResult = explorationResultFactory.newExplorationResult();

        explorationResult.addEventEntryResult(
                messageEventEntryEvaluator.messageEntry("WOLF_ATTACK_EXPLORATION_EVENT_ENTRY_1")
        ).addEventEntryResult(
                messageEventEntryEvaluator.messageEntry("WOLF_ATTACK_EXPLORATION_EVENT_ENTRY_2")
        ).addEventEntryResult(
                messageEventEntryEvaluator.messageEntry("WOLF_ATTACK_EXPLORATION_EVENT_ENTRY_3")
        );

        //TODO: add coins

        explorationResult.addEventEntryResult(
                messageEventEntryEvaluator.messageEntry("WOLF_ATTACK_EXPLORATION_EVENT_ENTRY_4")
        ).addEventEntryResult(
                messageEventEntryEvaluator.messageEntry("WOLF_ATTACK_EXPLORATION_EVENT_ENTRY_5")
        );

        return explorationResult;
    }
}
