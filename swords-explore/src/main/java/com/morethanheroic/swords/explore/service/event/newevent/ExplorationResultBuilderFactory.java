package com.morethanheroic.swords.explore.service.event.newevent;

import com.morethanheroic.swords.explore.domain.ExplorationEventDefinition;
import com.morethanheroic.swords.explore.service.event.ExplorationResultFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExplorationResultBuilderFactory {

    @Autowired
    private ObjectFactory<ExplorationResultBuilder> explorationResultBuilder;

    @Autowired
    private ExplorationResultFactory explorationResultFactory;

    public ExplorationResultBuilder newExplorationResultBuilder(final UserEntity userEntity, final ExplorationEventDefinition explorationEventDefinition) {
        return explorationResultBuilder.getObject()
                .initialize(userEntity, explorationResultFactory.newExplorationResult(explorationEventDefinition));
    }
}
