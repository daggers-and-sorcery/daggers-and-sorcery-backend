package com.morethanheroic.swords.scavenging.service;

import com.morethanheroic.entity.service.factory.EntityFactory;
import com.morethanheroic.swords.metadata.factory.MetadataEntityFactory;
import com.morethanheroic.swords.scavenging.domain.ScavengingEntity;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.service.UserEntityFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScavengingEntityFactory implements EntityFactory<ScavengingEntity> {

    private final UserEntityFactory userEntityFactory;
    private final MetadataEntityFactory metadataEntityFactory;

    @Override
    public ScavengingEntity getEntity(int id) {
        final UserEntity userEntity = userEntityFactory.getEntity(id);

        return new ScavengingEntity(userEntity.getId(), metadataEntityFactory.getNumericEntity(userEntity, "SCAVENGING_POINTS"), metadataEntityFactory.getTextEntity(userEntity, "SCAVENGING_ENABLED"));
    }
}
