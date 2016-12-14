package com.morethanheroic.swords.metadata.service;

import com.morethanheroic.swords.metadata.domain.NumericMetadataEntity;
import com.morethanheroic.swords.metadata.domain.TextMetadataEntity;
import com.morethanheroic.swords.metadata.repository.repository.MetadataMapper;
import com.morethanheroic.swords.metadata.service.cache.MetadataDefinitionCache;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MetadataEntityFactory {

    @NonNull
    private final MetadataDefinitionCache metadataDefinitionCache;

    @NonNull
    private final MetadataMapper metadataMapper;

    public TextMetadataEntity getTextEntity(UserEntity userEntity, String metadataName) {
        return new TextMetadataEntity(userEntity, metadataDefinitionCache.getDefinition(metadataName), metadataMapper);
    }

    public NumericMetadataEntity getNumericEntity(UserEntity userEntity, String metadataName) {
        return new NumericMetadataEntity(userEntity, metadataDefinitionCache.getDefinition(metadataName), metadataMapper);
    }
}
