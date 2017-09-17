package com.morethanheroic.swords.explore.service.event.impl.sevgard.quest.cursedheroes;

import com.morethanheroic.swords.combat.domain.CombatType;
import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.service.event.ExplorationEvent;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationContext;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationResultStageBuilderFactory;
import com.morethanheroic.swords.explore.service.event.newevent.ImprovedExplorationEventHandler;
import com.morethanheroic.swords.explore.service.event.newevent.condition.ConditionFactory;
import com.morethanheroic.swords.item.service.definition.cache.ItemDefinitionCache;
import com.morethanheroic.swords.monster.service.cache.MonsterDefinitionCache;
import com.morethanheroic.swords.quest.service.definition.cache.QuestDefinitionCache;
import lombok.RequiredArgsConstructor;

@ExplorationEvent
@RequiredArgsConstructor
public class CursedHeroesToTheForestExplorationEventHandler extends ImprovedExplorationEventHandler {

    private static final int EVENT_ID = 41;

    private static final int STARTER_STAGE = 0;
    private static final int VAMPIRE_WARRIOR_STAGE = 1;
    private static final int NARTHLOK_STAGE = 2;
    private static final int EXAMINING_THE_DOOR_STAGE = 3;
    private static final int FIGHTING_UTOLKA_STAGE = 4;

    private static final int VAMPIRE_WARRIOR_ID = 30;
    private static final int NARTHLOK_THE_LITERATE_ID = 1111;
    private static final int UTOLKA_ID = 2222;

    private static final int ONYX_ITEM_ID = 208;

    private static final int CURSED_HEROES_QUEST_ID = 4;

    private static final int FIGHTING_THE_VAMPIRE_WARRIOR_QUEST_STAGE_ID = 4;
    private static final int FIGHTING_NARTHLOK_QUEST_STAGE_ID = 5;
    private static final int EXAMINING_THE_DOOR_QUEST_STAGE_ID = 6;
    private static final int FIGHTING_UTOLKA_QUEST_STAGE_ID = 7;

    private final QuestDefinitionCache questDefinitionCache;
    private final ExplorationResultStageBuilderFactory explorationResultStageBuilderFactory;
    private final ConditionFactory conditionFactory;
    private final ItemDefinitionCache itemDefinitionCache;
    private final MonsterDefinitionCache monsterDefinitionCache;

    @Override
    public ExplorationResult handleExplore(ExplorationContext explorationContext) {
        return explorationResultStageBuilderFactory.newBuilder()
                .addStage(STARTER_STAGE,
                        explorationResultBuilder -> explorationResultBuilder
                                .newMessageEntry("CURSED_HEROES_QUEST_EXPLORATION_EVENT_ENTRY_14")
                                .newMessageEntry("CURSED_HEROES_QUEST_EXPLORATION_EVENT_ENTRY_15")
                                .newMessageEntry("CURSED_HEROES_QUEST_EXPLORATION_EVENT_ENTRY_16")
                                .newMessageEntry("CURSED_HEROES_QUEST_EXPLORATION_EVENT_ENTRY_17")
                                .newCombatEntry(monsterDefinitionCache.getDefinition(VAMPIRE_WARRIOR_ID), questDefinitionCache.getDefinition(CURSED_HEROES_QUEST_ID), FIGHTING_THE_VAMPIRE_WARRIOR_QUEST_STAGE_ID)
                                .build()
                )
                .addStage(VAMPIRE_WARRIOR_STAGE,
                        explorationResultBuilder -> explorationResultBuilder
                                .newIsCombatRunningMultiWayPath(CombatType.QUEST_4)
                                .isFailure(
                                        explorationResultBuilder1 -> explorationResultBuilder1
                                                .newMessageEntry("CURSED_HEROES_QUEST_EXPLORATION_EVENT_ENTRY_17")
                                                .continueCombatEntry(CombatType.QUEST_4)
                                                .build()
                                )
                                .isSuccess(
                                        explorationResultBuilder1 -> explorationResultBuilder1
                                                .newMessageEntry("CURSED_HEROES_QUEST_EXPLORATION_EVENT_ENTRY_18")
                                                .newCombatEntry(monsterDefinitionCache.getDefinition(NARTHLOK_THE_LITERATE_ID), questDefinitionCache.getDefinition(CURSED_HEROES_QUEST_ID), FIGHTING_NARTHLOK_QUEST_STAGE_ID)
                                                .build()
                                )
                                .build()
                )
                .addStage(NARTHLOK_STAGE,
                        explorationResultBuilder -> explorationResultBuilder
                                .newIsCombatRunningMultiWayPath(CombatType.QUEST_4)
                                .isFailure(
                                        explorationResultBuilder1 -> explorationResultBuilder1
                                                .newMessageEntry("CURSED_HEROES_QUEST_EXPLORATION_EVENT_ENTRY_18")
                                                .continueCombatEntry(CombatType.QUEST_4)
                                                .build()
                                )
                                .isSuccess(
                                        explorationResultBuilder1 -> explorationResultBuilder1
                                                .newMessageEntry("CURSED_HEROES_QUEST_EXPLORATION_EVENT_ENTRY_19")
                                                .newMessageEntry("CURSED_HEROES_QUEST_EXPLORATION_EVENT_ENTRY_20")
                                                .newUpdateQuestStage(questDefinitionCache.getDefinition(CURSED_HEROES_QUEST_ID), EXAMINING_THE_DOOR_QUEST_STAGE_ID)
                                                .build()
                                )
                                .build()
                )
                .addStage(EXAMINING_THE_DOOR_STAGE,
                        explorationResultBuilder -> explorationResultBuilder
                                .newMessageEntry("CURSED_HEROES_QUEST_EXPLORATION_EVENT_ENTRY_21")
                                .newMessageEntry("CURSED_HEROES_QUEST_EXPLORATION_EVENT_ENTRY_22")
                                .newConditionalMultiWayPath(
                                        conditionFactory.newConditionBuilder()
                                                .newItemCondition(itemDefinitionCache.getDefinition(ONYX_ITEM_ID))
                                                .build()
                                )
                                .isFailure(
                                        explorationResultBuilder1 -> explorationResultBuilder1
                                                .newMessageEntry("CURSED_HEROES_QUEST_EXPLORATION_EVENT_ENTRY_23")
                                                .build()
                                )
                                .isSuccess(
                                        explorationResultBuilder1 -> explorationResultBuilder1
                                                .newMessageEntry("CURSED_HEROES_QUEST_EXPLORATION_EVENT_ENTRY_24")
                                                .newMessageEntry("CURSED_HEROES_QUEST_EXPLORATION_EVENT_ENTRY_25")
                                                .newMessageEntry("CURSED_HEROES_QUEST_EXPLORATION_EVENT_ENTRY_26")
                                                .newMessageEntry("CURSED_HEROES_QUEST_EXPLORATION_EVENT_ENTRY_27")
                                                .newRemoveItemEntry(itemDefinitionCache.getDefinition(ONYX_ITEM_ID))
                                                .newCombatEntry(monsterDefinitionCache.getDefinition(UTOLKA_ID), CURSED_HEROES_QUEST_ID, FIGHTING_UTOLKA_QUEST_STAGE_ID)
                                                .build()
                                )
                                .build()
                )
                .addStage(FIGHTING_UTOLKA_STAGE,
                        explorationResultBuilder -> explorationResultBuilder
                                .newIsCombatRunningMultiWayPath(CombatType.QUEST_4)
                                .isSuccess(
                                        explorationResultBuilder1 -> explorationResultBuilder1
                                                //TODO!
                                                .build()
                                )
                                .isFailure(
                                        explorationResultBuilder1 -> explorationResultBuilder1
                                                .newMessageEntry("CURSED_HEROES_QUEST_EXPLORATION_EVENT_ENTRY_26")
                                                .newMessageEntry("CURSED_HEROES_QUEST_EXPLORATION_EVENT_ENTRY_27")
                                                .continueCombatEntry(CombatType.QUEST_4)
                                                .build()
                                )
                                .build()
                )
                .runStage(explorationContext);
    }

    @Override
    public ExplorationResult handleInfo(ExplorationContext explorationContext) {
        return null;
    }

    @Override
    public boolean isValidNextStageAtStage(int stage, int nextStage) {
        return false;
    }

    @Override
    public int getId() {
        return EVENT_ID;
    }
}
