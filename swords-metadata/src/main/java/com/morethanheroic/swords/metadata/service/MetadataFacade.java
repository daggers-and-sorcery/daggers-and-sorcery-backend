package com.morethanheroic.swords.metadata.service;

import com.morethanheroic.swords.dependency.InjectAtReturn;
import com.morethanheroic.swords.metadata.domain.MetadataDefinition;
import com.morethanheroic.swords.metadata.domain.MetadataEntity;
import com.morethanheroic.swords.metadata.service.cache.MetadataDefinitionCache;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetadataFacade {

    @Autowired
    private MetadataDefinitionCache metadataDefinitionCache;

    public MetadataEntity getEntity(UserEntity userEntity, String metadataName) {
        return getEntity(userEntity, metadataDefinitionCache.getDefinition(metadataName));
    }


    @InjectAtReturn
    public MetadataEntity getEntity(UserEntity userEntity, MetadataDefinition metadataDefinition) {
        return new MetadataEntity(userEntity, metadataDefinition);
    }
}
