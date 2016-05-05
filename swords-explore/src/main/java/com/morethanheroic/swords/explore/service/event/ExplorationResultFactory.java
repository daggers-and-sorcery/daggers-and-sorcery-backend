package com.morethanheroic.swords.explore.service.event;

import com.morethanheroic.swords.explore.domain.ExplorationResult;
import org.springframework.stereotype.Service;

@Service
public class ExplorationResultFactory {

    public ExplorationResult newExplorationResult() {
        return new ExplorationResult();
    }
}
