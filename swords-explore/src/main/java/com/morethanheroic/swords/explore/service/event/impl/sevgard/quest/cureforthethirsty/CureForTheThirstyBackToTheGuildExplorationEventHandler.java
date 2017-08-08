package com.morethanheroic.swords.explore.service.event.impl.sevgard.quest.cureforthethirsty;

import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.service.event.ExplorationEvent;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationContext;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationResultStageBuilderFactory;
import com.morethanheroic.swords.explore.service.event.newevent.ImprovedExplorationEventHandler;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.item.service.definition.cache.ItemDefinitionCache;
import com.morethanheroic.swords.quest.service.definition.cache.QuestDefinitionCache;
import com.morethanheroic.swords.recipe.service.definition.cache.RecipeDefinitionCache;
import com.morethanheroic.swords.recipe.service.learn.RecipeLearnerService;
import lombok.RequiredArgsConstructor;

@ExplorationEvent
@RequiredArgsConstructor
public class CureForTheThirstyBackToTheGuildExplorationEventHandler extends ImprovedExplorationEventHandler {

    private static final int EVENT_ID = 33;

    private static final int STARTER_STAGE = 0;

    private static final int CURE_FOR_THE_THIRSTY_QUEST_ID = 2;
    private static final int CURE_FOR_THE_THIRSTY_QUEST_NEXT_STAGE_ID = 9;

    private static final int CURING_THE_THIRSTY_RECIPE_ID = 63;
    private static final int CURING_THE_THIRSTY_POTION_ID = 201;

    private final QuestDefinitionCache questDefinitionCache;
    private final ExplorationResultStageBuilderFactory explorationResultStageBuilderFactory;
    private final RecipeDefinitionCache recipeDefinitionCache;
    private final RecipeLearnerService recipeLearnerService;
    private final InventoryEntityFactory inventoryEntityFactory;
    private final ItemDefinitionCache itemDefinitionCache;

    @Override
    public ExplorationResult handleExplore(final ExplorationContext explorationContext) {
        throw new IllegalStateException("Handle explore called unexpectedly under quest: " + questDefinitionCache.getDefinition(CURE_FOR_THE_THIRSTY_QUEST_ID).getName());
    }

    @Override
    public ExplorationResult handleInfo(ExplorationContext explorationContext) {
        return explorationResultStageBuilderFactory.newBuilder()
                .addStage(STARTER_STAGE,
                        explorationResultBuilder -> explorationResultBuilder
                                .newMessageEntry("CURE_FOR_THE_THIRSTY_QUEST_EXPLORATION_EVENT_ENTRY_32")
                                .newMessageEntry("CURE_FOR_THE_THIRSTY_QUEST_EXPLORATION_EVENT_ENTRY_33")
                                .newMessageEntry("CURE_FOR_THE_THIRSTY_QUEST_EXPLORATION_EVENT_ENTRY_34")
                                .newMessageEntry("CURE_FOR_THE_THIRSTY_QUEST_EXPLORATION_EVENT_ENTRY_35")
                                .newMessageEntry("CURE_FOR_THE_THIRSTY_QUEST_EXPLORATION_EVENT_ENTRY_36")
                                .newAttributeAttemptEntry(SkillAttribute.HERBLORE, 5)
                                .isSuccess(
                                        explorationResultBuilder1 -> explorationResultBuilder1
                                                .newMessageEntry("CURE_FOR_THE_THIRSTY_QUEST_EXPLORATION_EVENT_ENTRY_39")
                                                .newCustomLogicEntry(() -> {
                                                    recipeLearnerService.learnRecipe(explorationContext.getUserEntity(), recipeDefinitionCache.getDefinition(CURING_THE_THIRSTY_RECIPE_ID));
                                                    inventoryEntityFactory.getEntity(explorationContext.getUserEntity()).addItem(itemDefinitionCache.getDefinition(CURING_THE_THIRSTY_POTION_ID), 1);
                                                })
                                                .newUpdateQuestStage(questDefinitionCache.getDefinition(CURE_FOR_THE_THIRSTY_QUEST_ID), CURE_FOR_THE_THIRSTY_QUEST_NEXT_STAGE_ID)
                                                .newFinishQuestEntry(questDefinitionCache.getDefinition(CURE_FOR_THE_THIRSTY_QUEST_ID))
                                                .build()
                                )
                                .isFailure(
                                        explorationResultBuilder1 -> explorationResultBuilder1
                                                .newMessageEntry("CURE_FOR_THE_THIRSTY_QUEST_EXPLORATION_EVENT_ENTRY_37")
                                                .newMessageEntry("CURE_FOR_THE_THIRSTY_QUEST_EXPLORATION_EVENT_ENTRY_38")
                                                .newCustomLogicEntry(() -> recipeLearnerService.learnRecipe(explorationContext.getUserEntity(), recipeDefinitionCache.getDefinition(CURING_THE_THIRSTY_RECIPE_ID)))
                                                .newUpdateQuestStage(questDefinitionCache.getDefinition(CURE_FOR_THE_THIRSTY_QUEST_ID), CURE_FOR_THE_THIRSTY_QUEST_NEXT_STAGE_ID)
                                                .newFinishQuestEntry(questDefinitionCache.getDefinition(CURE_FOR_THE_THIRSTY_QUEST_ID))
                                                .build()
                                )
                                .build()
                )
                .runStage(explorationContext);
    }

    @Override
    public boolean isValidNextStageAtStage(int stage, int nextStage) {
        return true;
    }

    @Override
    public int getId() {
        return EVENT_ID;
    }
}
