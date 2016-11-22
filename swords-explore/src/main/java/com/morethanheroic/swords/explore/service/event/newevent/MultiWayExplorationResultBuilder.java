package com.morethanheroic.swords.explore.service.event.newevent;

import com.morethanheroic.swords.explore.domain.ExplorationResult;

public class MultiWayExplorationResultBuilder {

    private ExplorationResultBuilder explorationResultBuilder;
    private ExplorationResult explorationResult;
    private boolean isSuccess;

    public MultiWayExplorationResultBuilder(final ExplorationResultBuilder explorationResultBuilder, final boolean isSuccess) {
        this.explorationResultBuilder = explorationResultBuilder;
        this.isSuccess = isSuccess;
    }

    public MultiWayExplorationResultBuilder isSuccess(ExplorationResultRuntime runnable) {
        if (!isSuccess) {
            return this;
        }

        explorationResult = runnable.run(explorationResultBuilder);

        return this;
    }

    public MultiWayExplorationResultBuilder isFailure(ExplorationResultRuntime runnable) {
        if (isSuccess) {
            return this;
        }

        explorationResult = runnable.run(explorationResultBuilder);

        return this;
    }

    public ExplorationResult build() {
        return explorationResult;
    }
}
