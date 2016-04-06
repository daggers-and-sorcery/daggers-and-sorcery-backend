package com.morethanheroic.swords.explore.domain.event;

import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.domain.context.ExplorationContext;
import com.morethanheroic.swords.explore.domain.event.entry.ExplorationEventEntry;
import com.morethanheroic.swords.explore.domain.event.result.ExplorationEventEntryResult;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
public class ExplorationEventDefinition {

    private final List<ExplorationEventEntry> explorationEventEntries;

    public ExplorationResult explore(UserEntity userEntity) {
        final List<ExplorationEventEntryResult> result = new ArrayList<>();

        for (ExplorationEventEntry explorationEventEntry : explorationEventEntries) {
            result.add(explorationEventEntry.handleExploration(
                    ExplorationContext.builder()
                            .userEntity(userEntity)
                            .build()
            ));
        }

        return ExplorationResult.builder()
                .explorationEventEntryResults(result)
                .build();
    }
}
