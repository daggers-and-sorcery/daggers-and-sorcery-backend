package com.morethanheroic.swords.metadata.service.loader;

import com.google.common.collect.ImmutableList;
import com.morethanheroic.swords.definition.loader.DefinitionLoader;
import com.morethanheroic.swords.definition.service.loader.NumericXmlDefinitionLoader;
import com.morethanheroic.swords.metadata.domain.MetadataDefinition;
import com.morethanheroic.swords.metadata.service.loader.domain.RawMetadataDefinition;
import com.morethanheroic.swords.metadata.service.transformer.MetadataDefinitionTransformer;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class MetadataDefinitionLoader implements DefinitionLoader<MetadataDefinition> {

    private static final String METADATA_DEFINITION_LOCATION = "classpath:data/metadata/definition/";
    private static final String METADATA_SCHEMA_LOCATION = "classpath:data/metadata/schema.xsd";
    private static final int METADATA_COUNT_TO_LOAD = 100;

    @NonNull
    private final NumericXmlDefinitionLoader numericXmlDefinitionLoader;

    @NonNull
    private final MetadataDefinitionTransformer metadataDefinitionTransformer;

    @Override
    public List<MetadataDefinition> loadDefinitions() throws IOException {
        return loadRawMetadataDefinitions().stream().map(metadataDefinitionTransformer::transform).collect(
                collectingAndThen(toList(), ImmutableList::copyOf)
        );
    }

    @SuppressWarnings("unchecked")
    private List<RawMetadataDefinition> loadRawMetadataDefinitions() throws IOException {
        return numericXmlDefinitionLoader.loadDefinitions(RawMetadataDefinition.class, METADATA_DEFINITION_LOCATION,
                METADATA_SCHEMA_LOCATION, METADATA_COUNT_TO_LOAD);
    }
}
