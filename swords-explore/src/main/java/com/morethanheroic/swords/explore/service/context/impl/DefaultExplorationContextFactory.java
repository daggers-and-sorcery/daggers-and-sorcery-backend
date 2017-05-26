package com.morethanheroic.swords.explore.service.context.impl;

import com.morethanheroic.session.domain.SessionEntity;
import com.morethanheroic.swords.explore.domain.context.ExplorationContext;
import com.morethanheroic.swords.explore.domain.event.ExplorationEventLocation;
import com.morethanheroic.swords.explore.service.ExplorationEventChooser;
import com.morethanheroic.swords.explore.service.cache.ExplorationEventHandlerCache;
import com.morethanheroic.swords.explore.service.context.ExplorationContextFactory;
import com.morethanheroic.swords.explore.service.event.ExplorationEventHandler;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationAssignmentContext;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("production")
@RequiredArgsConstructor
public class DefaultExplorationContextFactory implements ExplorationContextFactory {

    private final ExplorationEventChooser explorationEventChooser;
    private final ExplorationEventHandlerCache explorationEventHandlerCache;

    @Override
    public ExplorationContext newExplorationContext(final UserEntity userEntity, final SessionEntity sessionEntity, final ExplorationEventLocation location, final int nextStage) {
        final ExplorationEventHandler explorationEvent;

        if (!userEntity.hasActiveExplorationEvent()) {
            explorationEvent = explorationEventChooser.getEvent(location,
                ExplorationAssignmentContext.builder()
                    .userEntity(userEntity)
                    .build()
            );
        } else {
            explorationEvent = explorationEventHandlerCache.getHandler(userEntity.getActiveExplorationEvent());
        }

        return ExplorationContext.builder()
                .event(explorationEvent)
                .stage(nextStage)
                .build();
    }
}
