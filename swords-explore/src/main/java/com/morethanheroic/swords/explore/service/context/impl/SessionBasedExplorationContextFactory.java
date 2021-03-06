package com.morethanheroic.swords.explore.service.context.impl;

import com.morethanheroic.session.domain.SessionEntity;
import com.morethanheroic.swords.explore.domain.context.ExplorationContext;
import com.morethanheroic.swords.explore.service.ExplorationEventChooser;
import com.morethanheroic.swords.explore.service.cache.ExplorationEventHandlerCache;
import com.morethanheroic.swords.explore.service.context.ExplorationContextFactory;
import com.morethanheroic.swords.explore.service.event.ExplorationEventHandler;
import com.morethanheroic.swords.zone.domain.ExplorationZone;
import com.morethanheroic.swords.explore.service.event.newevent.ExplorationAssignmentContext;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@Profile("development")
@RequiredArgsConstructor
public class SessionBasedExplorationContextFactory implements ExplorationContextFactory {

    private static final String EXPLORATION_EVENT_OVERRIDE_SESSION_ENTRY_KEY = "EXPLORATION_EVENT_OVERRIDE";

    private final ExplorationEventHandlerCache explorationEventHandlerCache;
    private final ExplorationEventChooser explorationEventChooser;

    @Override
    public ExplorationContext newExplorationContext(final UserEntity userEntity, final SessionEntity sessionEntity, ExplorationZone location, int nextStage) {
        final ExplorationEventHandler explorationEvent;

        if (userEntity.hasActiveExplorationEvent()) {
            explorationEvent = explorationEventHandlerCache.getHandler(userEntity.getActiveExplorationEvent());
        } else {
            explorationEvent = explorationEventHandlerCache.getHandler(getEventId(userEntity, sessionEntity, location));
        }

        return ExplorationContext.builder()
                .event(explorationEvent)
                .stage(nextStage)
                .build();
    }

    private int getEventId(final UserEntity userEntity, final SessionEntity sessionEntity, final ExplorationZone location) {
        if (sessionEntity.isAttributeSet(EXPLORATION_EVENT_OVERRIDE_SESSION_ENTRY_KEY)) {
            return (Integer) sessionEntity.getAttribute(EXPLORATION_EVENT_OVERRIDE_SESSION_ENTRY_KEY);
        }

        return explorationEventChooser.getEvent(location,
            ExplorationAssignmentContext.builder()
                .userEntity(userEntity)
                .build()
        ).getId();
    }
}
