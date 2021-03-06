package com.morethanheroic.swords.explore.service.event.impl.sevgard.whisperingwoods;

import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.service.event.ExplorationEvent;
import com.morethanheroic.swords.explore.service.event.MultiStageExplorationEventHandler;
import com.morethanheroic.swords.explore.service.event.cache.ExplorationEventDefinitionCache;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationResultBuilderFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;

@ExplorationEvent
public class WolfAttackExplorationEventHandler extends MultiStageExplorationEventHandler {

    private static final int EVENT_ID = 11;

    private static final int WOLF_MONSTER_ID = 7;

    private static final int COMBAT_STAGE = 1;

    @Autowired
    private ExplorationResultBuilderFactory explorationResultBuilderFactory;

    @Autowired
    private ExplorationEventDefinitionCache explorationEventDefinitionCache;

    @Override
    public int getId() {
        return 11;
    }

    @Override
    public ExplorationResult explore(UserEntity userEntity) {
        return explorationResultBuilderFactory
                .newExplorationResultBuilder(userEntity, explorationEventDefinitionCache.getDefinition(EVENT_ID))
                .newMessageEntry("WOLF_ATTACK_EXPLORATION_EVENT_ENTRY_1")
                .newMessageEntry("WOLF_ATTACK_EXPLORATION_EVENT_ENTRY_2")
                .newMessageEntry("WOLF_ATTACK_EXPLORATION_EVENT_ENTRY_3")
                .newCombatEntry(WOLF_MONSTER_ID, EVENT_ID, COMBAT_STAGE)
                .build();
    }

    @Override
    public ExplorationResult explore(UserEntity userEntity, int stage) {
        if (stage == COMBAT_STAGE) {
            userEntity.resetActiveExploration();

            return explorationResultBuilderFactory
                    .newExplorationResultBuilder(userEntity, explorationEventDefinitionCache.getDefinition(EVENT_ID))
                    .newMessageEntry("WOLF_ATTACK_EXPLORATION_EVENT_ENTRY_4")
                    .newMessageEntry("WOLF_ATTACK_EXPLORATION_EVENT_ENTRY_5")
                    .build();
        }

        return explorationResultBuilderFactory
                .newExplorationResultBuilder(userEntity, explorationEventDefinitionCache.getDefinition(EVENT_ID))
                .build();
    }

    @Override
    public ExplorationResult info(UserEntity userEntity, int stage) {
        if (stage == COMBAT_STAGE) {
            return explorationResultBuilderFactory
                    .newExplorationResultBuilder(userEntity, explorationEventDefinitionCache.getDefinition(EVENT_ID))
                    .newMessageEntry("WOLF_ATTACK_EXPLORATION_EVENT_ENTRY_3")
                    .continueCombatEntry()
                    .build();
        }

        return explorationResultBuilderFactory
                .newExplorationResultBuilder(userEntity, explorationEventDefinitionCache.getDefinition(EVENT_ID))
                .build();
    }

    @Override
    public boolean isValidNextStageAtStage(int stage, int nextStage) {
        return true;
    }
}
