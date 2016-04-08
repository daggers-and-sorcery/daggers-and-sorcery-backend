package com.morethanheroic.swords.metadata.service.transformer;

import com.morethanheroic.swords.definition.transformer.DefinitionTransformer;
import com.morethanheroic.swords.metadata.domain.MetadataValueDefinition;
import com.morethanheroic.swords.metadata.service.loader.domain.RawMetadataValueDefinition;
import org.springframework.stereotype.Service;

@Service
public class MetadataValueDefinitionTransformer implements DefinitionTransformer<MetadataValueDefinition, RawMetadataValueDefinition> {

    @Override
    public MetadataValueDefinition transform(RawMetadataValueDefinition rawDefinition) {
        return MetadataValueDefinition.builder()
                .id(rawDefinition.getId())
                .value(rawDefinition.getValue())
                .build();
    }
}
