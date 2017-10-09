package com.morethanheroic.swords.explore.service.event.impl.sevgard.woodlandcemetery;

import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.service.event.ExplorationEvent;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationAssignmentContext;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationContext;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationResultStageBuilderFactory;
import com.morethanheroic.swords.explore.service.event.newevent.ImprovedExplorationEventHandler;
import com.morethanheroic.swords.quest.service.QuestStateCalculator;
import com.morethanheroic.swords.quest.service.definition.cache.QuestDefinitionCache;
import lombok.RequiredArgsConstructor;

@ExplorationEvent
@RequiredArgsConstructor
public class FindingTheCryptExplorationEventHandler extends ImprovedExplorationEventHandler {

    private static final int EVENT_ID = 42;

    private static final int CURSED_HEROES_QUEST_ID = 4;
    private static final int CURSED_HEROES_QUEST_NOT_STARTED_STATE_ID = 0;

    private static final int STARTER_STAGE = 0;
    private static final int ACCEPT_QUEST_STAGE = 1;
    private static final int DECLINE_QUEST_STAGE = 2;

    private final QuestStateCalculator questStateCalculator;
    private final QuestDefinitionCache questDefinitionCache;
    private final ExplorationResultStageBuilderFactory explorationResultStageBuilderFactory;

    @Override
    public ExplorationResult handleExplore(ExplorationContext explorationContext) {
        return explorationResultStageBuilderFactory.newBuilder()
                .addStage(STARTER_STAGE,
                        explorationResultBuilder1 -> explorationResultBuilder1
                                .newMessageEntry("FINDING_THE_CRYPT_EXPLORATION_EVENT_ENTRY_1")
                                .newMessageEntry("FINDING_THE_CRYPT_EXPLORATION_EVENT_ENTRY_2")
                                .newQuestDialogEntry(questDefinitionCache.getDefinition(CURSED_HEROES_QUEST_ID), ACCEPT_QUEST_STAGE, DECLINE_QUEST_STAGE)
                                .setEventStage(EVENT_ID, STARTER_STAGE)
                                .build()
                )
                .addStage(ACCEPT_QUEST_STAGE,
                        explorationResultBuilder1 -> explorationResultBuilder1
                                .newMessageEntry("FINDING_THE_CRYPT_EXPLORATION_EVENT_ENTRY_3")
                                .newAcceptQuestEntry(questDefinitionCache.getDefinition(CURSED_HEROES_QUEST_ID))
                                .resetExploration()
                                .build()
                )
                .addStage(DECLINE_QUEST_STAGE,
                        explorationResultBuilder1 -> explorationResultBuilder1
                                .newMessageEntry("FINDING_THE_CRYPT_EXPLORATION_EVENT_ENTRY_4")
                                .resetExploration()
                                .build()
                )
                .runStage(explorationContext);
    }

    @Override
    public ExplorationResult handleInfo(ExplorationContext explorationContext) {
        return explorationResultStageBuilderFactory.newBuilder()
                .addStage(STARTER_STAGE,
                        explorationResultBuilder1 -> explorationResultBuilder1
                                .newMessageEntry("FINDING_THE_CRYPT_EXPLORATION_EVENT_ENTRY_3")
                                .newQuestDialogEntry(questDefinitionCache.getDefinition(CURSED_HEROES_QUEST_ID), ACCEPT_QUEST_STAGE, DECLINE_QUEST_STAGE)
                                .build()
                )
                .runStage(explorationContext);

    }

    @Override
    public boolean shouldAssign(final ExplorationAssignmentContext explorationAssignmentContext) {
        return questStateCalculator.getQuestStage(explorationAssignmentContext.getUserEntity(), questDefinitionCache.getDefinition(CURSED_HEROES_QUEST_ID))
                == CURSED_HEROES_QUEST_NOT_STARTED_STATE_ID;
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
