package com.morethanheroic.swords.metadata.service.cache;

import com.morethanheroic.swords.definition.cache.DefinitionCache;
import com.morethanheroic.swords.metadata.domain.MetadataDefinition;
import com.morethanheroic.swords.metadata.service.loader.MetadataDefinitionLoader;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class MetadataDefinitionCache implements DefinitionCache<String, MetadataDefinition> {

    @Autowired
    private MetadataDefinitionLoader metadataDefinitionLoader;

    private final Map<String, MetadataDefinition> metadataDefinitionsMap = new HashMap<>();

    @PostConstruct
    public void init() throws IOException {
        final List<MetadataDefinition> metadataDefinitions = metadataDefinitionLoader.loadDefinitions();

        log.info("Loaded " + metadataDefinitions.size() + " metadata definitions.");

        for (MetadataDefinition metadataDefinition : metadataDefinitions) {
            metadataDefinitionsMap.put(metadataDefinition.getName(), metadataDefinition);
        }
    }

    @Override
    public MetadataDefinition getDefinition(String key) {
        return metadataDefinitionsMap.get(key);
    }
}
