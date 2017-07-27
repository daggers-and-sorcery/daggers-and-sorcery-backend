package com.morethanheroic.swords.explore.service.event;

import com.morethanheroic.swords.explore.domain.ExplorationEventDefinition;
import com.morethanheroic.swords.explore.domain.ExplorationResult;
import org.springframework.stereotype.Service;

/**
 * A factory implementation to create {@link ExplorationResult} instances.
 */
@Service
public class ExplorationResultFactory {

    public ExplorationResult newExplorationResult(final ExplorationEventDefinition explorationEventDefinition) {
        if (explorationEventDefinition != null) {
            return new ExplorationResult(explorationEventDefinition.getName(), explorationEventDefinition.getRarity(), explorationEventDefinition.getTerrain());
        }

        return newEmptyExplorationResult();
    }

    public ExplorationResult newEmptyExplorationResult() {
        return new ExplorationResult(null, null, null);
    }
}
