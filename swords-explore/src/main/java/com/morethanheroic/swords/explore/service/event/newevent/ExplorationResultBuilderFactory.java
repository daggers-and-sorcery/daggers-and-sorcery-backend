package com.morethanheroic.swords.explore.service.event.newevent;

import com.morethanheroic.swords.explore.domain.ExplorationEventDefinition;
import com.morethanheroic.swords.explore.service.event.ExplorationResultFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExplorationResultBuilderFactory {

    private final ObjectFactory<ExplorationResultBuilder> explorationResultBuilder;
    private final ExplorationResultFactory explorationResultFactory;

    public ExplorationResultBuilder newExplorationResultBuilder(final UserEntity userEntity, final ExplorationEventDefinition explorationEventDefinition) {
        return explorationResultBuilder.getObject()
                .initialize(userEntity, explorationResultFactory.newExplorationResult(explorationEventDefinition));
    }
}
