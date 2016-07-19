package com.morethanheroic.swords.explore.service.event.newevent;

import com.morethanheroic.swords.explore.domain.ExplorationResult;

public interface ExplorationResultRuntime {

    ExplorationResult run(ExplorationResultBuilder explorationResultBuilder);
}
