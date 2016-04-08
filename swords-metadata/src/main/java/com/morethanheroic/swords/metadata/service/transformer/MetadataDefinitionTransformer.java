package com.morethanheroic.swords.metadata.service.transformer;

import com.morethanheroic.swords.definition.transformer.DefinitionTransformer;
import com.morethanheroic.swords.metadata.domain.MetadataDefinition;
import com.morethanheroic.swords.metadata.service.loader.domain.RawMetadataDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class MetadataDefinitionTransformer implements DefinitionTransformer<MetadataDefinition, RawMetadataDefinition> {

    @Autowired
    private MetadataValueDefinitionTransformer metadataValueDefinitionTransformer;

    @Override
    public MetadataDefinition transform(RawMetadataDefinition rawDefinition) {
        return MetadataDefinition.builder()
                .id(rawDefinition.getId())
                .name(rawDefinition.getName())
                .values(rawDefinition.getValues().stream().map(metadataValueDefinitionTransformer::transform).collect(Collectors.toList()))
                .build();
    }
}
