package com.morethanheroic.swords.explore.service.event.impl.sevgard.whisperingwoods;

import com.morethanheroic.swords.attribute.domain.GeneralAttribute;
import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.service.event.ExplorationEventLocationType;
import com.morethanheroic.swords.explore.service.event.ExplorationEvent;
import com.morethanheroic.swords.explore.service.event.ExplorationEventDefinition;
import com.morethanheroic.swords.explore.service.event.ExplorationResultFactory;
import com.morethanheroic.swords.explore.service.event.evaluator.AttributeAttemptEventEntryEvaluator;
import com.morethanheroic.swords.explore.service.event.evaluator.CombatEventEntryEvaluator;
import com.morethanheroic.swords.explore.service.event.evaluator.MessageEventEntryEvaluator;
import com.morethanheroic.swords.explore.service.event.evaluator.domain.AttributeAttemptEventEntryEvaluatorResult;
import com.morethanheroic.swords.explore.service.event.evaluator.domain.CombatEventEntryEvaluatorResult;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@ExplorationEvent
public class ForestJourneyExplorationEventDefinition extends ExplorationEventDefinition {

    private static final int GOBLIN_GUARD_MONSTER_ID = 2;

    @Autowired
    private ExplorationResultFactory explorationResultFactory;

    @Autowired
    private CombatEventEntryEvaluator combatEventEntryEvaluator;

    @Autowired
    private MessageEventEntryEvaluator messageEventEntryEvaluator;

    @Autowired
    private AttributeAttemptEventEntryEvaluator attributeAttemptEventEntryEvaluator;

    private MonsterDefinition opponent;

    @PostConstruct
    private void initialize() {
        opponent = combatEventEntryEvaluator.convertMonsterIdToDefinition(GOBLIN_GUARD_MONSTER_ID);
    }

    @Override
    public int getId() {
        return 9;
    }

    @Override
    public ExplorationResult explore(UserEntity userEntity) {
        final ExplorationResult explorationResult = explorationResultFactory.newExplorationResult();

        explorationResult.addEventEntryResult(
                messageEventEntryEvaluator.messageEntry("FOREST_JOURNEY_EXPLORATION_EVENT_ENTRY_1")
        ).addEventEntryResult(
                messageEventEntryEvaluator.messageEntry("FOREST_JOURNEY_EXPLORATION_EVENT_ENTRY_2")
        ).addEventEntryResult(
                messageEventEntryEvaluator.messageEntry("FOREST_JOURNEY_EXPLORATION_EVENT_ENTRY_3")
        );

        final CombatEventEntryEvaluatorResult combatEventEntryEvaluatorResult = combatEventEntryEvaluator.calculateCombat(userEntity, opponent);

        explorationResult.addEventEntryResult(combatEventEntryEvaluatorResult.getResult());

        /*
        if (!combatEventEntryEvaluatorResult.getCombatResult().isPlayerVictory()) {
            return explorationResult;
        }
        */

        explorationResult.addEventEntryResult(
                messageEventEntryEvaluator.messageEntry("FOREST_JOURNEY_EXPLORATION_EVENT_ENTRY_4")
        );

        final AttributeAttemptEventEntryEvaluatorResult attemptResult = attributeAttemptEventEntryEvaluator.attributeAttempt(userEntity, GeneralAttribute.DEXTERITY, 8);

        explorationResult.addEventEntryResult(attemptResult.getResult());

        if (attemptResult.isSuccessful()) {
            explorationResult.addEventEntryResult(
                    messageEventEntryEvaluator.messageEntry("FOREST_JOURNEY_EXPLORATION_EVENT_ENTRY_5")
            );

            final CombatEventEntryEvaluatorResult secondCombatEventEntryEvaluatorResult = combatEventEntryEvaluator.calculateCombat(userEntity, opponent);

            explorationResult.addEventEntryResult(secondCombatEventEntryEvaluatorResult.getResult());

            /*
            if (!secondCombatEventEntryEvaluatorResult.getCombatResult().isPlayerVictory()) {
                return explorationResult;
            }
            */

            explorationResult.addEventEntryResult(
                    messageEventEntryEvaluator.messageEntry("FOREST_JOURNEY_EXPLORATION_EVENT_ENTRY_6")
            );
        } else {
            explorationResult.addEventEntryResult(
                    messageEventEntryEvaluator.messageEntry("FOREST_JOURNEY_EXPLORATION_EVENT_ENTRY_7")
            );
        }

        return explorationResult;
    }

    @Override
    public ExplorationEventLocationType getLocation() {
        return ExplorationEventLocationType.WHISPERING_WOODS;
    }
}
