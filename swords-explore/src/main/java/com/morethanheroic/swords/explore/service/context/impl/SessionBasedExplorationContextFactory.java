package com.morethanheroic.swords.explore.service.context.impl;

import com.morethanheroic.session.domain.SessionEntity;
import com.morethanheroic.swords.explore.domain.context.ExplorationContext;
import com.morethanheroic.swords.explore.service.ExplorationEventChooser;
import com.morethanheroic.swords.explore.service.cache.ExplorationEventDefinitionCache;
import com.morethanheroic.swords.explore.service.context.ExplorationContextFactory;
import com.morethanheroic.swords.explore.service.event.ExplorationEventDefinition;
import com.morethanheroic.swords.explore.service.event.ExplorationEventLocationType;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("development")
public class SessionBasedExplorationContextFactory implements ExplorationContextFactory {

    private static final int NO_EVENT = 0;

    private static final String EXPLORATION_EVENT_OVERRIDE_SESSION_ENTRY_KEY = "EXPLORATION_EVENT_OVERRIDE";

    @Autowired
    private ExplorationEventDefinitionCache explorationEventDefinitionCache;

    @Autowired
    private ExplorationEventChooser explorationEventChooser;

    @Override
    public ExplorationContext newExplorationContext(final UserEntity userEntity, final SessionEntity sessionEntity, ExplorationEventLocationType location, int nextStage) {
        final ExplorationEventDefinition explorationEvent;

        if (userEntity.getActiveExplorationEvent() == NO_EVENT) {
            explorationEvent = explorationEventDefinitionCache.getDefinition(getEventId(sessionEntity, location));
        } else {
            explorationEvent = explorationEventDefinitionCache.getDefinition(userEntity.getActiveExplorationEvent());
        }

        return ExplorationContext.builder()
                .event(explorationEvent)
                .stage(nextStage)
                .build();
    }

    private int getEventId(final SessionEntity sessionEntity, final ExplorationEventLocationType location) {
        if (sessionEntity.isAttributeSet(EXPLORATION_EVENT_OVERRIDE_SESSION_ENTRY_KEY)) {
            return (Integer) sessionEntity.getAttribute(EXPLORATION_EVENT_OVERRIDE_SESSION_ENTRY_KEY);
        }

        return explorationEventChooser.getEvent(location).getId();
    }
}
