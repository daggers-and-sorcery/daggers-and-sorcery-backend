package com.morethanheroic.swords.explore.domain.event;

import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.domain.event.entry.ExplorationEventEntry;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ExplorationEventDefinition {

    private final List<ExplorationEventEntry> explorationEventEntries;

    public ExplorationResult explore(UserEntity userEntity) {
        return null;
    }
}
