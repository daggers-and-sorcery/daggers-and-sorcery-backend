package com.morethanheroic.swords.explore.service.event.impl.sevgard.quest.cursedheroes;

import com.google.common.collect.Lists;
import com.morethanheroic.swords.attribute.domain.GeneralAttribute;
import com.morethanheroic.swords.attribute.service.manipulator.UserBasicAttributeManipulator;
import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.service.event.ExplorationEvent;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationContext;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationResultStageBuilderFactory;
import com.morethanheroic.swords.explore.service.event.newevent.ImprovedExplorationEventHandler;
import com.morethanheroic.swords.explore.service.event.newevent.ReplyOption;
import com.morethanheroic.swords.explore.service.event.newevent.condition.ConditionFactory;
import com.morethanheroic.swords.item.service.definition.cache.ItemDefinitionCache;
import com.morethanheroic.swords.quest.service.definition.cache.QuestDefinitionCache;
import lombok.RequiredArgsConstructor;

@ExplorationEvent
@RequiredArgsConstructor
public class CursedHeroesStartingExplorationEventHandler extends ImprovedExplorationEventHandler {

    private static final int EVENT_ID = 40;

    private static final int STARTER_STAGE = 0;
    private static final int BRIBING_STAGE = 1;
    private static final int MEETING_THE_MAGE_STAGE = 2;

    private static final int CURSED_HEROES_QUEST_ID = 4;

    private static final int GOLD_COIN_ID = 50;
    private static final int GOLD_COIN_REQUIRED_AMOUNT = 2;

    private static final int MEETING_THE_MAGE_QUEST_STAGE_ID = 2;
    private static final int TO_THE_FOREST_QUEST_STAGE_ID = 3;

    private final QuestDefinitionCache questDefinitionCache;
    private final ExplorationResultStageBuilderFactory explorationResultStageBuilderFactory;
    private final UserBasicAttributeManipulator userBasicAttributeManipulator;
    private final ConditionFactory conditionFactory;
    private final ItemDefinitionCache itemDefinitionCache;

    @Override
    public ExplorationResult handleExplore(ExplorationContext explorationContext) {
        throw new IllegalStateException("Handle explore called unexpectedly under quest: " + questDefinitionCache.getDefinition(CURSED_HEROES_QUEST_ID).getName());
    }

    @Override
    public ExplorationResult handleInfo(ExplorationContext explorationContext) {
        return explorationResultStageBuilderFactory.newBuilder()
                .addStage(STARTER_STAGE,
                        explorationResultBuilder -> explorationResultBuilder
                                .newMessageEntry("CURSED_HEROES_QUEST_EXPLORATION_EVENT_ENTRY_1")
                                .newMessageEntry("CURSED_HEROES_QUEST_EXPLORATION_EVENT_ENTRY_2")
                                .newMessageEntry("CURSED_HEROES_QUEST_EXPLORATION_EVENT_ENTRY_3")
                                .newOptionEntry(
                                        ReplyOption.builder()
                                                .message("GOBLIN_KING_EXPLORATION_EVENT_QUESTION_REPLY_1")
                                                .stage(BRIBING_STAGE)
                                                .build()
                                )
                                .build()
                )
                .addStage(BRIBING_STAGE,
                        explorationResultBuilder -> explorationResultBuilder
                                .newConditionalMultiWayPath(
                                        Lists.newArrayList(
                                                conditionFactory.newConditionBuilder()
                                                        .newItemCondition(itemDefinitionCache.getDefinition(GOLD_COIN_ID), GOLD_COIN_REQUIRED_AMOUNT)
                                                        .build()
                                        )
                                )
                                .isSuccess(
                                        explorationResultBuilder1 -> explorationResultBuilder1
                                                .newMessageEntry("CURSED_HEROES_QUEST_EXPLORATION_EVENT_ENTRY_5")
                                                .newRemoveItemEntry(itemDefinitionCache.getDefinition(GOLD_COIN_ID), GOLD_COIN_REQUIRED_AMOUNT)
                                                .newUpdateQuestStage(questDefinitionCache.getDefinition(CURSED_HEROES_QUEST_ID), MEETING_THE_MAGE_QUEST_STAGE_ID)
                                                .build()
                                )
                                .isFailure(
                                        explorationResultBuilder1 -> explorationResultBuilder1
                                                .newMessageEntry("CURSED_HEROES_QUEST_EXPLORATION_EVENT_ENTRY_4")
                                                .build()
                                )
                                .build()
                )
                .addStage(MEETING_THE_MAGE_STAGE, explorationResultBuilder ->
                        explorationResultBuilder
                                .newMessageEntry("CURSED_HEROES_QUEST_EXPLORATION_EVENT_ENTRY_6")
                                .newMessageEntry("CURSED_HEROES_QUEST_EXPLORATION_EVENT_ENTRY_7")
                                .newMessageEntry("CURSED_HEROES_QUEST_EXPLORATION_EVENT_ENTRY_8")
                                .newMessageEntry("CURSED_HEROES_QUEST_EXPLORATION_EVENT_ENTRY_9")
                                .newCustomLogicEntry(() -> userBasicAttributeManipulator.decreaseMovement(explorationContext.getUserEntity(), 1))
                                .newAttributeAttemptEntry(GeneralAttribute.INTELLIGENCE, 16)
                                .isSuccess(
                                        explorationResultBuilder1 -> explorationResultBuilder1
                                                .newMessageEntry("CURSED_HEROES_QUEST_EXPLORATION_EVENT_ENTRY_12")
                                                .newMessageEntry("CURSED_HEROES_QUEST_EXPLORATION_EVENT_ENTRY_13")
                                                .newUpdateQuestStage(questDefinitionCache.getDefinition(CURSED_HEROES_QUEST_ID), TO_THE_FOREST_QUEST_STAGE_ID)
                                                .build()
                                )
                                .isFailure(
                                        explorationResultBuilder1 -> explorationResultBuilder1
                                                .newMessageEntry("CURSED_HEROES_QUEST_EXPLORATION_EVENT_ENTRY_10")
                                                .newMessageEntry("CURSED_HEROES_QUEST_EXPLORATION_EVENT_ENTRY_11")
                                                .build()
                                )
                                .build()
                )
                .runStage(explorationContext);
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
