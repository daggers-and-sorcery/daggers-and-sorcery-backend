package com.morethanheroic.swords.metadata.service;

import com.morethanheroic.swords.metadata.domain.definition.MetadataDefinition;
import com.morethanheroic.swords.metadata.repository.repository.MetadataMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MetadataPurger {

    private final MetadataMapper metadataMapper;

    /**
     * Remove all metadata that's related to the definition provided.
     *
     * @param metadataDefinition the metadata to remove all data for
     */
    public void removeAllMetadata(final MetadataDefinition metadataDefinition) {
        metadataMapper.deleteAllMetadataWithId(metadataDefinition.getId());
    }
}
