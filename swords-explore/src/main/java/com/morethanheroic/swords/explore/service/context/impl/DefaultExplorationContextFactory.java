package com.morethanheroic.swords.explore.service.context.impl;

import com.morethanheroic.session.domain.SessionEntity;
import com.morethanheroic.swords.explore.domain.context.ExplorationContext;
import com.morethanheroic.swords.explore.service.ExplorationEventChooser;
import com.morethanheroic.swords.explore.service.context.ExplorationContextFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("production")
public class DefaultExplorationContextFactory implements ExplorationContextFactory {

    @Autowired
    private ExplorationEventChooser explorationEventChooser;

    public ExplorationContext newExplorationContext(final UserEntity userEntity, final SessionEntity sessionEntity) {
        return ExplorationContext.builder()
                .event(explorationEventChooser.getEvent())
                .stage(0)
                .build();
    }
}
