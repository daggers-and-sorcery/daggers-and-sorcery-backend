package com.morethanheroic.swords.metadata.service.cache;

import com.morethanheroic.definition.cache.impl.MapBasedDefinitionCache;
import com.morethanheroic.swords.metadata.domain.definition.MetadataDefinition;
import com.morethanheroic.swords.metadata.service.loader.MetadataDefinitionLoader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MetadataDefinitionCache extends MapBasedDefinitionCache<String, MetadataDefinition> {

    public MetadataDefinitionCache(final MetadataDefinitionLoader metadataDefinitionLoader) throws IOException {
        super(
                metadataDefinitionLoader.loadDefinitions().stream()
                        .collect(Collectors.toMap(MetadataDefinition::getName, Function.identity()))
        );

        log.info("Loaded " + this.getSize() + " metadata definitions.");
    }
}
