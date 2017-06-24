package com.morethanheroic.swords.explore.service.event.impl.sevgard.quest;

import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationContext;
import com.morethanheroic.swords.explore.service.event.newevent.ImprovedExplorationEventHandler;
import lombok.RequiredArgsConstructor;

//@ExplorationEvent
@RequiredArgsConstructor
public class FindingTheGuildQuestFarmfieldsExplorationEventHandler extends ImprovedExplorationEventHandler {

    @Override
    public boolean isValidNextStageAtStage(int stage, int nextStage) {
        return false;
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public ExplorationResult handleExplore(ExplorationContext explorationContext) {
        return null;
    }

    @Override
    public ExplorationResult handleInfo(ExplorationContext explorationContext) {
        return null;
    }
}
