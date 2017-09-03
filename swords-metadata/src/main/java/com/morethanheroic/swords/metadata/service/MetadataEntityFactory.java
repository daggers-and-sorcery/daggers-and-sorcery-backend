package com.morethanheroic.swords.metadata.service;

import com.morethanheroic.swords.metadata.domain.entity.NumericMetadataEntity;
import com.morethanheroic.swords.metadata.domain.entity.TextMetadataEntity;
import com.morethanheroic.swords.metadata.repository.repository.MetadataMapper;
import com.morethanheroic.swords.metadata.service.cache.MetadataDefinitionCache;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MetadataEntityFactory {

    private final MetadataDefinitionCache metadataDefinitionCache;
    private final MetadataMapper metadataMapper;

    /**
     * Return the textual result of the metadata. The textual result is mapped from the numeric value based on the
     * mapping provided in the datapack.
     *
     * @param userEntity the user entity we are evaluating the metadata for
     * @param metadataId the id of the metadata
     * @return the actual value of the metadata
     */
    public TextMetadataEntity getTextEntity(final UserEntity userEntity, final String metadataId) {
        return new TextMetadataEntity(userEntity, metadataDefinitionCache.getDefinition(metadataId), metadataMapper);
    }

    public NumericMetadataEntity getNumericEntity(final UserEntity userEntity, final String metadataId) {
        return new NumericMetadataEntity(userEntity, metadataDefinitionCache.getDefinition(metadataId), metadataMapper);
    }
}
