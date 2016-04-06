package com.morethanheroic.swords.explore.service;

import com.morethanheroic.swords.explore.domain.ExplorationResult;
import com.morethanheroic.swords.explore.service.cache.ExplorationEventDefinitionCache;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExplorationFacade {

    @Autowired
    private ExplorationEventDefinitionCache explorationEventDefinitionCache;

    @Transactional
    public ExplorationResult explore(UserEntity userEntity) {
        //TODO: make this random
        return explorationEventDefinitionCache.getDefinition(0).explore(userEntity);
    }
}
