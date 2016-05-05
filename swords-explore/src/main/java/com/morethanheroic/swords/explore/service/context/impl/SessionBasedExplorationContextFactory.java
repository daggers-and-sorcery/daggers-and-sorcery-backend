package com.morethanheroic.swords.explore.service.context.impl;

import com.morethanheroic.session.domain.SessionEntity;
import com.morethanheroic.swords.explore.domain.context.ExplorationContext;
import com.morethanheroic.swords.explore.service.cache.ExplorationEventDefinitionCache;
import com.morethanheroic.swords.explore.service.context.ExplorationContextFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"development"})
public class SessionBasedExplorationContextFactory implements ExplorationContextFactory {

    private static final String EXPLORATION_EVENT_OVERRIDE_SESSION_ENTRY_KEY = "EXPLORATION_EVENT_OVERRIDE";

    @Autowired
    private ExplorationEventDefinitionCache explorationEventDefinitionCache;

    @Override
    public ExplorationContext newExplorationContext(UserEntity userEntity, SessionEntity sessionEntity) {
        return ExplorationContext.builder()
                .event(explorationEventDefinitionCache.getDefinition((Integer) sessionEntity.getAttribute(EXPLORATION_EVENT_OVERRIDE_SESSION_ENTRY_KEY)))
                .stage(0)
                .build();
    }
}
