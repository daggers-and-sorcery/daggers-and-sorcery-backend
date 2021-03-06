package com.morethanheroic.swords.explore.service.event.impl.sevgard.whisperingwoods;

import com.morethanheroic.swords.attribute.domain.GeneralAttribute;
import com.morethanheroic.swords.combat.domain.CombatType;
import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.service.event.ExplorationEvent;
import com.morethanheroic.swords.explore.service.event.ExplorationResultFactory;
import com.morethanheroic.swords.explore.service.event.MultiStageExplorationEventHandler;
import com.morethanheroic.swords.explore.service.event.cache.ExplorationEventDefinitionCache;
import com.morethanheroic.swords.explore.service.event.evaluator.CombatEventEntryEvaluator;
import com.morethanheroic.swords.explore.service.event.evaluator.MessageEventEntryEvaluator;
import com.morethanheroic.swords.explore.service.event.evaluator.attempt.AttributeAttemptEventEntryEvaluator;
import com.morethanheroic.swords.explore.service.event.evaluator.attempt.domain.AttributeAttemptEventEntryEvaluatorResult;
import com.morethanheroic.swords.explore.service.event.evaluator.domain.CombatEventEntryEvaluatorResult;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationResultBuilderFactory;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@ExplorationEvent
public class ForestJourneyExplorationEventHandler extends MultiStageExplorationEventHandler {

    private static final int EVENT_ID = 9;

    private static final int GOBLIN_GUARD_MONSTER_ID = 2;

    private static final int COMBAT_STAGE = 1;
    private static final int SECOND_COMBAT_STAGE = 2;

    @Autowired
    private ExplorationResultFactory explorationResultFactory;

    @Autowired
    private CombatEventEntryEvaluator combatEventEntryEvaluator;

    @Autowired
    private MessageEventEntryEvaluator messageEventEntryEvaluator;

    @Autowired
    private AttributeAttemptEventEntryEvaluator attributeAttemptEventEntryEvaluator;

    @Autowired
    private ExplorationResultBuilderFactory explorationResultBuilderFactory;

    @Autowired
    private ExplorationEventDefinitionCache explorationEventDefinitionCache;

    private MonsterDefinition opponent;

    @PostConstruct
    private void initialize() {
        opponent = combatEventEntryEvaluator.convertMonsterIdToDefinition(GOBLIN_GUARD_MONSTER_ID);
    }

    @Override
    public int getId() {
        return EVENT_ID;
    }

    @Override
    public ExplorationResult explore(UserEntity userEntity) {
        final ExplorationResult explorationResult = explorationResultFactory.newExplorationResult(explorationEventDefinitionCache.getDefinition(EVENT_ID));

        explorationResult.addEventEntryResult(
                messageEventEntryEvaluator.messageEntry("FOREST_JOURNEY_EXPLORATION_EVENT_ENTRY_1")
        ).addEventEntryResult(
                messageEventEntryEvaluator.messageEntry("FOREST_JOURNEY_EXPLORATION_EVENT_ENTRY_2")
        ).addEventEntryResult(
                messageEventEntryEvaluator.messageEntry("FOREST_JOURNEY_EXPLORATION_EVENT_ENTRY_3")
        );

        final CombatEventEntryEvaluatorResult combatEventEntryEvaluatorResult = combatEventEntryEvaluator.calculateCombat(userEntity, opponent, CombatType.EXPLORE);

        explorationResult.addEventEntryResult(combatEventEntryEvaluatorResult.getResult());

        if (!combatEventEntryEvaluatorResult.getResult().isPlayerDead()) {
            userEntity.setActiveExploration(EVENT_ID, COMBAT_STAGE);
        }

        return explorationResult;
    }

    @Override
    public ExplorationResult explore(UserEntity userEntity, int stage) {
        final ExplorationResult explorationResult = explorationResultFactory.newExplorationResult(explorationEventDefinitionCache.getDefinition(EVENT_ID));

        if (stage == COMBAT_STAGE) {
            explorationResult.addEventEntryResult(
                    messageEventEntryEvaluator.messageEntry("FOREST_JOURNEY_EXPLORATION_EVENT_ENTRY_4")
            );

            final AttributeAttemptEventEntryEvaluatorResult attemptResult = attributeAttemptEventEntryEvaluator.attributeAttempt(userEntity, GeneralAttribute.DEXTERITY, 8);

            explorationResult.addEventEntryResults(attemptResult.getResult());

            if (attemptResult.isSuccessful()) {
                explorationResult.addEventEntryResult(
                        messageEventEntryEvaluator.messageEntry("FOREST_JOURNEY_EXPLORATION_EVENT_ENTRY_5")
                );

                final CombatEventEntryEvaluatorResult secondCombatEventEntryEvaluatorResult = combatEventEntryEvaluator.calculateCombat(userEntity, opponent, CombatType.EXPLORE);

                explorationResult.addEventEntryResult(secondCombatEventEntryEvaluatorResult.getResult());

                if (!secondCombatEventEntryEvaluatorResult.getResult().isPlayerDead()) {
                    userEntity.setActiveExploration(EVENT_ID, SECOND_COMBAT_STAGE);
                }
            } else {
                explorationResult.addEventEntryResult(
                        messageEventEntryEvaluator.messageEntry("FOREST_JOURNEY_EXPLORATION_EVENT_ENTRY_7")
                );

                userEntity.resetActiveExploration();
            }
        } else if (stage == SECOND_COMBAT_STAGE) {
            explorationResult.addEventEntryResult(
                    messageEventEntryEvaluator.messageEntry("FOREST_JOURNEY_EXPLORATION_EVENT_ENTRY_6")
            );

            userEntity.resetActiveExploration();
        }

        return explorationResult;
    }

    @Override
    public ExplorationResult info(UserEntity userEntity, int stage) {
        if (stage == COMBAT_STAGE) {
            return explorationResultBuilderFactory
                    .newExplorationResultBuilder(userEntity, explorationEventDefinitionCache.getDefinition(EVENT_ID))
                    .newMessageEntry("FOREST_JOURNEY_EXPLORATION_EVENT_ENTRY_3")
                    .continueCombatEntry()
                    .build();
        } else if (stage == SECOND_COMBAT_STAGE) {
            return explorationResultBuilderFactory
                    .newExplorationResultBuilder(userEntity, explorationEventDefinitionCache.getDefinition(EVENT_ID))
                    .newMessageEntry("FOREST_JOURNEY_EXPLORATION_EVENT_ENTRY_5")
                    .continueCombatEntry()
                    .build();
        }

        return explorationResultFactory.newExplorationResult(explorationEventDefinitionCache.getDefinition(EVENT_ID));
    }

    @Override
    public boolean isValidNextStageAtStage(int stage, int nextStage) {
        if (stage == SECOND_COMBAT_STAGE && nextStage == SECOND_COMBAT_STAGE) {
            return true;
        } else if (stage == COMBAT_STAGE && nextStage == COMBAT_STAGE) {
            return true;
        }

        return false;
    }
}
