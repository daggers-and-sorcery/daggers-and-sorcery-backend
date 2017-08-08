package com.morethanheroic.swords.explore.service.event.impl.sevgard.quest.findingtheguild;

import com.morethanheroic.swords.attribute.domain.BasicAttribute;
import com.morethanheroic.swords.attribute.service.calc.BasicAttributeCalculator;
import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.service.event.ExplorationEvent;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationAssignmentContext;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationContext;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationResultStageBuilderFactory;
import com.morethanheroic.swords.explore.service.event.newevent.ImprovedExplorationEventHandler;
import com.morethanheroic.swords.explore.service.event.newevent.condition.ConditionFactory;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.item.service.definition.cache.ItemDefinitionCache;
import com.morethanheroic.swords.quest.service.definition.cache.QuestDefinitionCache;
import lombok.RequiredArgsConstructor;

@ExplorationEvent
@RequiredArgsConstructor
public class FindingTheGuildQuestFarmfieldsExplorationEventHandler extends ImprovedExplorationEventHandler {

    private static final int EVENT_ID = 26;

    private static final int ROBINIA_LOG_ID = 32;
    private static final int BRONZE_NAILS_ID = 196;

    private static final int STARTER_STAGE = 0;

    private static final int WITCHHUNTER_GUILD_JOIN_QUEST_ID = 1;
    private static final int WITCHHUNTER_GUILD_JOIN_QUEST_NEXT_STAGE_ID = 3;

    private final ExplorationResultStageBuilderFactory explorationResultStageBuilderFactory;
    private final QuestDefinitionCache questDefinitionCache;
    private final ConditionFactory conditionFactory;
    private final ItemDefinitionCache itemDefinitionCache;
    private final InventoryEntityFactory inventoryEntityFactory;
    private final BasicAttributeCalculator basicAttributeCalculator;

    @Override
    public ExplorationResult handleExplore(final ExplorationContext explorationContext) {
        throw new IllegalStateException("Handle explore called unexpectedly under quest: " + questDefinitionCache.getDefinition(WITCHHUNTER_GUILD_JOIN_QUEST_ID).getName());
    }

    @Override
    public ExplorationResult handleInfo(final ExplorationContext explorationContext) {
        final InventoryEntity inventoryEntity = inventoryEntityFactory.getEntity(explorationContext.getUserEntity());

        return explorationResultStageBuilderFactory.newBuilder()
                .addStage(STARTER_STAGE,
                        explorationResultBuilder1 -> explorationResultBuilder1
                                .newMessageEntry("FINDING_THE_GUILD_QUEST_STARTING_EXPLORATION_EVENT_ENTRY_15")
                                .newConditionalMultiWayPath(explorationContext,
                                        conditionFactory.newConditionBuilder()
                                                .newItemCondition(itemDefinitionCache.getDefinition(ROBINIA_LOG_ID), 10)
                                                .newItemCondition(itemDefinitionCache.getDefinition(BRONZE_NAILS_ID), 100)
                                                .newMovementCondition(10)
                                                .build()

                                )
                                .isFailure(
                                        explorationResultBuilder2 -> explorationResultBuilder2
                                                .newMessageEntry("FINDING_THE_GUILD_QUEST_STARTING_EXPLORATION_EVENT_ENTRY_16")
                                                .newMessageBoxEntry("FINDING_THE_GUILD_QUEST_STARTING_EXPLORATION_EVENT_ENTRY_19", inventoryEntity.getItemAmount(itemDefinitionCache.getDefinition(ROBINIA_LOG_ID)), 10, itemDefinitionCache.getDefinition(ROBINIA_LOG_ID).getName())
                                                .newMessageBoxEntry("FINDING_THE_GUILD_QUEST_STARTING_EXPLORATION_EVENT_ENTRY_19", inventoryEntity.getItemAmount(itemDefinitionCache.getDefinition(BRONZE_NAILS_ID)), 100, itemDefinitionCache.getDefinition(BRONZE_NAILS_ID).getName())
                                                .newMessageBoxEntry("FINDING_THE_GUILD_QUEST_STARTING_EXPLORATION_EVENT_ENTRY_19", basicAttributeCalculator.calculateAttributeValue(explorationContext.getUserEntity(), BasicAttribute.MOVEMENT).getActual().getValue(), 10, BasicAttribute.MOVEMENT.getName())
                                                .build()
                                )
                                .isSuccess(
                                        explorationResultBuilder2 -> explorationResultBuilder2
                                                .newMessageEntry("FINDING_THE_GUILD_QUEST_STARTING_EXPLORATION_EVENT_ENTRY_17")
                                                .newMessageEntry("FINDING_THE_GUILD_QUEST_STARTING_EXPLORATION_EVENT_ENTRY_18")
                                                .newRemoveItemEntry(itemDefinitionCache.getDefinition(ROBINIA_LOG_ID), 10)
                                                .newRemoveItemEntry(itemDefinitionCache.getDefinition(BRONZE_NAILS_ID), 100)
                                                .newRemoveMovementPoints(10)
                                                .newUpdateQuestStage(questDefinitionCache.getDefinition(WITCHHUNTER_GUILD_JOIN_QUEST_ID), WITCHHUNTER_GUILD_JOIN_QUEST_NEXT_STAGE_ID)
                                                .newContinueQuestEntry(questDefinitionCache.getDefinition(WITCHHUNTER_GUILD_JOIN_QUEST_ID))
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
    public boolean shouldAssign(ExplorationAssignmentContext explorationAssignmentContext) {
        return false;
    }

    @Override
    public int getId() {
        return EVENT_ID;
    }
}
