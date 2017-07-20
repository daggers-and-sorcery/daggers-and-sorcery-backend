package com.morethanheroic.swords.explore.service.event.impl.sevgard.quest.cureforthethirsty;

import com.morethanheroic.swords.attribute.domain.GeneralAttribute;
import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.combat.domain.CombatType;
import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.service.event.ExplorationEvent;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationContext;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationResultStageBuilderFactory;
import com.morethanheroic.swords.explore.service.event.newevent.ImprovedExplorationEventHandler;
import com.morethanheroic.swords.quest.service.definition.cache.QuestDefinitionCache;
import lombok.RequiredArgsConstructor;

@ExplorationEvent
@RequiredArgsConstructor
public class CureForTheThirstyApproachingIntoTheWoodsExplorationEventHandler extends ImprovedExplorationEventHandler {

    private static final int EVENT_ID = 31;

    private static final int STARTER_STAGE = 0;
    private static final int COMBAT_STAGE = 1;
    private static final int SECOND_COMBAT_STAGE = 2;
    private static final int THIRD_COMBAT_STAGE = 3;

    private static final int ZOMBIE_ID = 20;
    private static final int VOLKSTEIN_THE_NECROMANCER_ID = 27;

    private static final int CURE_FOR_THE_THIRSTY_QUEST_ID = 2;
    private static final int CURE_FOR_THE_THIRSTY_QUEST_FIRST_COMBAT_STAGE_ID = 3;
    private static final int CURE_FOR_THE_THIRSTY_QUEST_SECOND_COMBAT_STAGE_ID = 4;
    private static final int CURE_FOR_THE_THIRSTY_QUEST_THIRD_COMBAT_STAGE_ID = 5;
    private static final int CURE_FOR_THE_THIRSTY_QUEST_NEXT_STAGE_ID = 6;

    private final QuestDefinitionCache questDefinitionCache;
    private final ExplorationResultStageBuilderFactory explorationResultStageBuilderFactory;

    @Override
    public ExplorationResult handleExplore(final ExplorationContext explorationContext) {
        throw new IllegalStateException("Handle explore called unexpectedly under quest: " + questDefinitionCache.getDefinition(CURE_FOR_THE_THIRSTY_QUEST_ID).getName());
    }

    @Override
    public ExplorationResult handleInfo(ExplorationContext explorationContext) {
        return explorationResultStageBuilderFactory.newBuilder()
                .addStage(STARTER_STAGE,
                        explorationResultBuilder1 -> explorationResultBuilder1
                                .newMessageEntry("CURE_FOR_THE_THIRSTY_QUEST_EXPLORATION_EVENT_ENTRY_10")
                                .newMessageEntry("CURE_FOR_THE_THIRSTY_QUEST_EXPLORATION_EVENT_ENTRY_11")
                                .newMessageEntry("CURE_FOR_THE_THIRSTY_QUEST_EXPLORATION_EVENT_ENTRY_12")
                                .newAttributeAttemptEntry(SkillAttribute.LOCKPICKING, 8)
                                .isSuccess((explorationResultBuilder) -> explorationResultBuilder
                                        .newMessageEntry("CURE_FOR_THE_THIRSTY_QUEST_EXPLORATION_EVENT_ENTRY_13")
                                        .newMessageEntry("CURE_FOR_THE_THIRSTY_QUEST_EXPLORATION_EVENT_ENTRY_14")
                                        .newCombatEntry(ZOMBIE_ID, questDefinitionCache.getDefinition(CURE_FOR_THE_THIRSTY_QUEST_ID), CURE_FOR_THE_THIRSTY_QUEST_FIRST_COMBAT_STAGE_ID)
                                        .build()
                                )
                                .isFailure((explorationResultBuilder) -> explorationResultBuilder
                                        //TODO: What to do when the lockpicking is failed?!
                                        .build()
                                )
                                .build()
                )
                .addStage(COMBAT_STAGE,
                        explorationResultBuilder -> explorationResultBuilder
                                .newIsCombatRunningMultiWayPath(explorationContext, CombatType.QUEST_2)
                                .isSuccess(
                                        explorationResultBuilder1 -> explorationResultBuilder1
                                                .newMessageEntry("CURE_FOR_THE_THIRSTY_QUEST_EXPLORATION_EVENT_ENTRY_14")
                                                .continueCombatEntry(CombatType.QUEST_2)
                                                .build()
                                )
                                .isFailure(
                                        explorationResultBuilder1 -> explorationResultBuilder1
                                                .newMessageEntry("CURE_FOR_THE_THIRSTY_QUEST_EXPLORATION_EVENT_ENTRY_15")
                                                .newCombatEntry(ZOMBIE_ID, questDefinitionCache.getDefinition(CURE_FOR_THE_THIRSTY_QUEST_ID), CURE_FOR_THE_THIRSTY_QUEST_SECOND_COMBAT_STAGE_ID)
                                                .build()
                                )
                                .build()
                )
                .addStage(SECOND_COMBAT_STAGE,
                        explorationResultBuilder -> explorationResultBuilder
                                .newIsCombatRunningMultiWayPath(explorationContext, CombatType.QUEST_2)
                                .isSuccess(
                                        explorationResultBuilder1 -> explorationResultBuilder1
                                                .newMessageEntry("CURE_FOR_THE_THIRSTY_QUEST_EXPLORATION_EVENT_ENTRY_15")
                                                .continueCombatEntry(CombatType.QUEST_2)
                                                .build()
                                )
                                .isFailure(
                                        explorationResultBuilder1 -> explorationResultBuilder1
                                                .newMessageEntry("CURE_FOR_THE_THIRSTY_QUEST_EXPLORATION_EVENT_ENTRY_16")
                                                //TODO: Add mov requirement here!
                                                .newAttributeAttemptEntry(GeneralAttribute.PERCEPTION, 10)
                                                .isSuccess(
                                                        explorationResultBuilder2 -> explorationResultBuilder2
                                                                .newMessageEntry("CURE_FOR_THE_THIRSTY_QUEST_EXPLORATION_EVENT_ENTRY_18")
                                                                .newMessageEntry("CURE_FOR_THE_THIRSTY_QUEST_EXPLORATION_EVENT_ENTRY_19")
                                                                .newMessageEntry("CURE_FOR_THE_THIRSTY_QUEST_EXPLORATION_EVENT_ENTRY_20")
                                                                .newMessageEntry("CURE_FOR_THE_THIRSTY_QUEST_EXPLORATION_EVENT_ENTRY_21")
                                                                .newCombatEntry(VOLKSTEIN_THE_NECROMANCER_ID, questDefinitionCache.getDefinition(CURE_FOR_THE_THIRSTY_QUEST_ID), CURE_FOR_THE_THIRSTY_QUEST_THIRD_COMBAT_STAGE_ID)
                                                                .build()
                                                )
                                                .isFailure(
                                                        explorationResultBuilder2 -> explorationResultBuilder2
                                                                .newMessageEntry("CURE_FOR_THE_THIRSTY_QUEST_EXPLORATION_EVENT_ENTRY_17")
                                                                .build()
                                                )
                                                .build()
                                )
                                .build()
                )
                .addStage(THIRD_COMBAT_STAGE,
                        explorationResultBuilder -> explorationResultBuilder
                                .newIsCombatRunningMultiWayPath(explorationContext, CombatType.QUEST_2)
                                .isSuccess(
                                        explorationResultBuilder1 -> explorationResultBuilder1
                                                .newMessageEntry("CURE_FOR_THE_THIRSTY_QUEST_EXPLORATION_EVENT_ENTRY_21")
                                                .continueCombatEntry(CombatType.QUEST_2)
                                                .build()
                                )
                                .isFailure(
                                        explorationResultBuilder1 -> explorationResultBuilder1
                                                .newMessageEntry("CURE_FOR_THE_THIRSTY_QUEST_EXPLORATION_EVENT_ENTRY_22")
                                                .newMessageEntry("CURE_FOR_THE_THIRSTY_QUEST_EXPLORATION_EVENT_ENTRY_23")
                                                .newUpdateQuestStage(questDefinitionCache.getDefinition(CURE_FOR_THE_THIRSTY_QUEST_ID), CURE_FOR_THE_THIRSTY_QUEST_NEXT_STAGE_ID)
                                                .newContinueQuestEntry(questDefinitionCache.getDefinition(CURE_FOR_THE_THIRSTY_QUEST_ID))
                                                .build()
                                )
                                .build()
                )
                .runStage(explorationContext);
    }

    @Override
    public int getId() {
        return EVENT_ID;
    }

    @Override
    public boolean isValidNextStageAtStage(int stage, int nextStage) {
        return true;
    }
}
