package com.morethanheroic.swords.scavenging.service;

import com.morethanheroic.entity.service.factory.EntityFactory;
import com.morethanheroic.swords.metadata.service.MetadataEntityFactory;
import com.morethanheroic.swords.scavenging.domain.ScavengingEntity;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScavengingEntityFactory implements EntityFactory<ScavengingEntity, UserEntity> {

    private final MetadataEntityFactory metadataEntityFactory;

    @Override
    public ScavengingEntity getEntity(UserEntity userEntity) {
        return new ScavengingEntity(userEntity.getId(), metadataEntityFactory.getNumericEntity(userEntity, "SCAVENGING_POINTS"), metadataEntityFactory.getTextEntity(userEntity, "SCAVENGING_ENABLED"));
    }
}
