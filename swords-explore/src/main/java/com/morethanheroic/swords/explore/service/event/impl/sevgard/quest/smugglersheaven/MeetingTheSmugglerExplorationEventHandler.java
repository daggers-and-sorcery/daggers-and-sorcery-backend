package com.morethanheroic.swords.explore.service.event.impl.sevgard.quest.smugglersheaven;

import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.service.event.ExplorationEvent;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationAssignmentContext;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationContext;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationResultStageBuilderFactory;
import com.morethanheroic.swords.explore.service.event.newevent.ImprovedExplorationEventHandler;
import com.morethanheroic.swords.quest.service.definition.cache.QuestDefinitionCache;
import lombok.RequiredArgsConstructor;

@ExplorationEvent
@RequiredArgsConstructor
public class MeetingTheSmugglerExplorationEventHandler extends ImprovedExplorationEventHandler {

    private static final int EVENT_ID = 35;

    private static final int SMUGGLERS_HEAVEN_QUEST_ID = 3;
    private static final int SMUGGLERS_HEAVEN_QUEST_NEXT_STAGE_ID = 2;

    private static final int STARTER_STAGE = 0;

    private final QuestDefinitionCache questDefinitionCache;
    private final ExplorationResultStageBuilderFactory explorationResultStageBuilderFactory;

    @Override
    public ExplorationResult handleExplore(ExplorationContext explorationContext) {
        throw new IllegalStateException("Handle explore called unexpectedly under quest: " + questDefinitionCache.getDefinition(SMUGGLERS_HEAVEN_QUEST_ID).getName());
    }

    @Override
    public ExplorationResult handleInfo(ExplorationContext explorationContext) {
        return explorationResultStageBuilderFactory.newBuilder()
                .addStage(STARTER_STAGE,
                        explorationResultBuilder1 -> explorationResultBuilder1
                                .newMessageEntry("SMUGGLERS_HEAVEN_QUEST_EXPLORATION_EVENT_ENTRY_1")
                                .newMessageEntry("SMUGGLERS_HEAVEN_QUEST_EXPLORATION_EVENT_ENTRY_2")
                                .newUpdateQuestStage(questDefinitionCache.getDefinition(SMUGGLERS_HEAVEN_QUEST_ID), SMUGGLERS_HEAVEN_QUEST_NEXT_STAGE_ID)
                                .newContinueQuestEntry(questDefinitionCache.getDefinition(SMUGGLERS_HEAVEN_QUEST_ID))
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
