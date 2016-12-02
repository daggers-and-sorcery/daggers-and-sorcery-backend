package com.morethanheroic.swords.explore.service.event.newevent;

import org.springframework.stereotype.Service;

@Service
public class MultiWayExplorationResultBuilderFactory {

    public MultiWayExplorationResultBuilder newMultiWayExplorationResultBuilder(final ExplorationResultBuilder explorationResultBuilder, final boolean isSuccess) {
        return new MultiWayExplorationResultBuilder(explorationResultBuilder, isSuccess);
    }

    public MultiWayExplorationResultBuilder newSuccessBasedMultiWayExplorationResultBuilder(final ExplorationResultBuilder explorationResultBuilder) {
        return new MultiWayExplorationResultBuilder(explorationResultBuilder, true);
    }

    public MultiWayExplorationResultBuilder newFailureBasedMultiWayExplorationResultBuilder(final ExplorationResultBuilder explorationResultBuilder) {
        return new MultiWayExplorationResultBuilder(explorationResultBuilder, false);
    }
}
