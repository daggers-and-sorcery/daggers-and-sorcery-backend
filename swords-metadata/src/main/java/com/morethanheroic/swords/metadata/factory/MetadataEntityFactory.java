package com.morethanheroic.swords.metadata.factory;

import com.morethanheroic.swords.metadata.domain.MetadataEntity;
import com.morethanheroic.swords.metadata.repository.repository.MetadataMapper;
import com.morethanheroic.swords.metadata.service.cache.MetadataDefinitionCache;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MetadataEntityFactory {

    @NonNull
    private final MetadataDefinitionCache metadataDefinitionCache;

    @NonNull
    private final MetadataMapper metadataMapper;

    public MetadataEntity getEntity(UserEntity userEntity, String metadataName) {
        return new MetadataEntity(userEntity, metadataDefinitionCache.getDefinition(metadataName), metadataMapper);
    }
}
