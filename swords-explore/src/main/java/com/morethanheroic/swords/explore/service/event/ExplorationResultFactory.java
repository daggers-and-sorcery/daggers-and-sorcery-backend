package com.morethanheroic.swords.explore.service.event;

import com.morethanheroic.swords.explore.domain.ExplorationEventDefinition;
import com.morethanheroic.swords.explore.domain.ExplorationResult;
import org.springframework.stereotype.Service;

@Service
public class ExplorationResultFactory {

    public ExplorationResult newExplorationResult(final ExplorationEventDefinition explorationEventDefinition) {
        if (explorationEventDefinition != null) {
            return new ExplorationResult(explorationEventDefinition.getRarity(), explorationEventDefinition.getTerrain());
        }

        return new ExplorationResult(null, null);
    }
}
