package com.morethanheroic.swords.explore.service.event.impl.sevgard.whisperingwoods;

import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.service.event.ExplorationEvent;
import com.morethanheroic.swords.explore.service.event.ExplorationEventDefinition;
import com.morethanheroic.swords.explore.service.event.ExplorationEventLocationType;
import com.morethanheroic.swords.explore.service.event.ExplorationResultFactory;
import com.morethanheroic.swords.explore.service.event.evaluator.CombatEventEntryEvaluator;
import com.morethanheroic.swords.explore.service.event.evaluator.MessageEventEntryEvaluator;
import com.morethanheroic.swords.explore.service.event.evaluator.domain.CombatEventEntryEvaluatorResult;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;

@ExplorationEvent
public class WolfAttackExplorationEventDefinition extends ExplorationEventDefinition {

    private static final int WOLF_MONSTER_ID = 7;

    @Autowired
    private ExplorationResultFactory explorationResultFactory;

    @Autowired
    private CombatEventEntryEvaluator combatEventEntryEvaluator;

    @Autowired
    private MessageEventEntryEvaluator messageEventEntryEvaluator;

    @Override
    public int getId() {
        return 11;
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

        final CombatEventEntryEvaluatorResult secondCombatEventEntryEvaluatorResult = combatEventEntryEvaluator.calculateCombat(userEntity, combatEventEntryEvaluator.convertMonsterIdToDefinition(WOLF_MONSTER_ID));

        explorationResult.addEventEntryResult(secondCombatEventEntryEvaluatorResult.getResult());

        if (!secondCombatEventEntryEvaluatorResult.getCombatResult().isPlayerVictory()) {
            return explorationResult;
        }

        explorationResult.addEventEntryResult(
                messageEventEntryEvaluator.messageEntry("WOLF_ATTACK_EXPLORATION_EVENT_ENTRY_4")
        ).addEventEntryResult(
                messageEventEntryEvaluator.messageEntry("WOLF_ATTACK_EXPLORATION_EVENT_ENTRY_5")
        );

        return explorationResult;
    }
}
