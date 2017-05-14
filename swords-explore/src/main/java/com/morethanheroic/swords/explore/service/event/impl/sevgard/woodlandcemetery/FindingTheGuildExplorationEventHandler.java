package com.morethanheroic.swords.explore.service.event.impl.sevgard.woodlandcemetery;

import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.service.event.ExplorationEvent;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationContext;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationResultStageBuilderFactory;
import com.morethanheroic.swords.explore.service.event.newevent.ImprovedExplorationEventHandler;
import lombok.RequiredArgsConstructor;

@ExplorationEvent
@RequiredArgsConstructor
public class FindingTheGuildExplorationEventHandler extends ImprovedExplorationEventHandler {

    private static final int EVENT_ID = 24;

    private static final int STARTER_STAGE = 0;

    private final ExplorationResultStageBuilderFactory explorationResultStageBuilderFactory;

    @Override
    public ExplorationResult handleExplore(final ExplorationContext explorationContext) {
        return explorationResultStageBuilderFactory.newBuilder()
                .addStage(STARTER_STAGE,
                        explorationResultBuilder1 -> explorationResultBuilder1
                                .newMessageEntry("FINDING_THE_GUILD_EXPLORATION_EVENT_ENTRY_1")
                                .newMessageEntry("FINDING_THE_GUILD_EXPLORATION_EVENT_ENTRY_2")
                                .newMessageEntry("FINDING_THE_GUILD_EXPLORATION_EVENT_ENTRY_3")
                                .newMessageEntry("FINDING_THE_GUILD_EXPLORATION_EVENT_ENTRY_4")
                                .newMessageEntry("FINDING_THE_GUILD_EXPLORATION_EVENT_ENTRY_5")
                                .newMessageEntry("FINDING_THE_GUILD_EXPLORATION_EVENT_ENTRY_6")
                                .newMessageEntry("FINDING_THE_GUILD_EXPLORATION_EVENT_ENTRY_7")
                                .newMessageEntry("FINDING_THE_GUILD_EXPLORATION_EVENT_ENTRY_8")
                                .newMessageEntry("FINDING_THE_GUILD_EXPLORATION_EVENT_ENTRY_9")
                                .build()
                )
                .runStage(explorationContext);
    }

    @Override
    public ExplorationResult handleInfo(ExplorationContext explorationContext) {
        return null;
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
