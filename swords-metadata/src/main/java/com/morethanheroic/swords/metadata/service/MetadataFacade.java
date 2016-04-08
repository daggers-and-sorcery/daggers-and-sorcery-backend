package com.morethanheroic.swords.metadata.service;

import com.morethanheroic.swords.dependency.InjectAtReturn;
import com.morethanheroic.swords.metadata.domain.MetadataDefinition;
import com.morethanheroic.swords.metadata.domain.MetadataEntity;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class MetadataFacade {

    @InjectAtReturn
    public MetadataEntity getEntity(UserEntity userEntity, MetadataDefinition metadataDefinition) {
        return new MetadataEntity(userEntity, metadataDefinition);
    }
}
