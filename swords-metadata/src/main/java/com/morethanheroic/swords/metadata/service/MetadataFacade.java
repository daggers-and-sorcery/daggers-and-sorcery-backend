package com.morethanheroic.swords.metadata.service;

import com.morethanheroic.swords.metadata.domain.MetadataEntity;
import com.morethanheroic.swords.metadata.factory.MetadataEntityFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MetadataFacade {

    @NonNull
    private final MetadataEntityFactory metadataEntityFactory;

    public MetadataEntity getEntity(UserEntity userEntity, String metadataName) {
        return metadataEntityFactory.getEntity(userEntity, metadataName);
    }
}
