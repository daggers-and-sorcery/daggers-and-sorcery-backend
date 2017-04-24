package com.morethanheroic.swords.metadata.service.transformer;

import com.morethanheroic.swords.definition.transformer.DefinitionTransformer;
import com.morethanheroic.swords.metadata.domain.MetadataDefinition;
import com.morethanheroic.swords.metadata.service.loader.domain.RawMetadataDefinition;
import com.morethanheroic.swords.metadata.service.loader.domain.RawMetadataValueDefinition;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MetadataDefinitionTransformer implements DefinitionTransformer<MetadataDefinition, RawMetadataDefinition> {

    private final MetadataValueDefinitionTransformer metadataValueDefinitionTransformer;

    @Override
    public MetadataDefinition transform(RawMetadataDefinition rawDefinition) {
        return MetadataDefinition.builder()
                .id(rawDefinition.getId())
                .name(rawDefinition.getName())
                .values(
                        getValues(rawDefinition).stream()
                                .map(metadataValueDefinitionTransformer::transform)
                                .collect(Collectors.toList())
                )
                .build();
    }

    private List<RawMetadataValueDefinition> getValues(RawMetadataDefinition rawDefinition) {
        return rawDefinition.getValues() != null ? rawDefinition.getValues() : Collections.emptyList();
    }
}
