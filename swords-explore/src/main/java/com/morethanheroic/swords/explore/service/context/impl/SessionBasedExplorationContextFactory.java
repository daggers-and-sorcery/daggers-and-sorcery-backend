package com.morethanheroic.swords.explore.service.context.impl;

import com.morethanheroic.session.domain.SessionEntity;
import com.morethanheroic.swords.explore.domain.context.ExplorationContext;
import com.morethanheroic.swords.explore.service.cache.ExplorationEventDefinitionCache;
import com.morethanheroic.swords.explore.service.context.ExplorationContextFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Profile({"development"})
public class SessionBasedExplorationContextFactory implements ExplorationContextFactory {

    private static final String EXPLORATION_EVENT_OVERRIDE_SESSION_ENTRY_KEY = "EXPLORATION_EVENT_OVERRIDE";

    @Autowired
    private ExplorationEventDefinitionCache explorationEventDefinitionCache;

    @Autowired
    private Random random;

    @Override
    public ExplorationContext newExplorationContext(final UserEntity userEntity, final SessionEntity sessionEntity) {
        return ExplorationContext.builder()
                .event(explorationEventDefinitionCache.getDefinition(getEventId(sessionEntity)))
                .stage(0)
                .build();
    }

    private int getEventId(final SessionEntity sessionEntity) {
        if (sessionEntity.isAttributeSet(EXPLORATION_EVENT_OVERRIDE_SESSION_ENTRY_KEY)) {
            return (Integer) sessionEntity.getAttribute(EXPLORATION_EVENT_OVERRIDE_SESSION_ENTRY_KEY);
        }

        return random.nextInt(explorationEventDefinitionCache.getSize()) + 1;
    }
}
