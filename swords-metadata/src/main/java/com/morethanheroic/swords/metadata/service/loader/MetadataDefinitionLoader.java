package com.morethanheroic.swords.metadata.service.loader;

import com.google.common.collect.ImmutableList;
import com.morethanheroic.definition.loader.DefinitionLoader;
import com.morethanheroic.swords.metadata.domain.definition.MetadataDefinition;
import com.morethanheroic.swords.metadata.service.loader.domain.RawMetadataDefinition;
import com.morethanheroic.swords.metadata.service.transformer.MetadataDefinitionTransformer;
import com.morethanheroic.xml.service.loader.NumericXmlDefinitionLoader;
import com.morethanheroic.xml.service.loader.domain.NumericDefinitionLoadingContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class MetadataDefinitionLoader implements DefinitionLoader<MetadataDefinition> {

    private static final String METADATA_DEFINITION_LOCATION = "classpath:data/metadata/definition/";
    private static final String METADATA_SCHEMA_LOCATION = "classpath:data/metadata/schema.xsd";

    private final NumericXmlDefinitionLoader numericXmlDefinitionLoader;
    private final MetadataDefinitionTransformer metadataDefinitionTransformer;

    @Override
    public List<MetadataDefinition> loadDefinitions() {
        return loadRawMetadataDefinitions().stream()
                .map(metadataDefinitionTransformer::transform)
                .collect(
                        collectingAndThen(toList(), ImmutableList::copyOf)
                );
    }

    private List<RawMetadataDefinition> loadRawMetadataDefinitions() {
        return numericXmlDefinitionLoader.loadDefinitions(
                NumericDefinitionLoadingContext.<RawMetadataDefinition>builder()
                        .clazz(RawMetadataDefinition.class)
                        .resourcePath(METADATA_DEFINITION_LOCATION)
                        .schemaPath(METADATA_SCHEMA_LOCATION)
                        .build()
        );
    }
}
