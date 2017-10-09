package com.morethanheroic.swords.metadata.service.transformer;

import com.morethanheroic.definition.transformer.DefinitionTransformer;
import com.morethanheroic.swords.metadata.domain.definition.MetadataValueDefinition;
import com.morethanheroic.swords.metadata.service.loader.domain.RawMetadataValueDefinition;
import org.springframework.stereotype.Service;

@Service
public class MetadataValueDefinitionTransformer implements DefinitionTransformer<MetadataValueDefinition, RawMetadataValueDefinition> {

    @Override
    public MetadataValueDefinition transform(RawMetadataValueDefinition rawDefinition) {
        return MetadataValueDefinition.builder()
                .id(rawDefinition.getId())
                .name(rawDefinition.getName())
                .build();
    }
}
