package com.morethanheroic.swords.loot.service.loader;

import com.google.common.collect.ImmutableList;
import com.morethanheroic.definition.loader.DefinitionLoader;
import com.morethanheroic.swords.definition.service.loader.NumericXmlDefinitionLoader;
import com.morethanheroic.swords.definition.service.loader.domain.NumericDefinitionLoadingContext;
import com.morethanheroic.swords.loot.domain.LootDefinition;
import com.morethanheroic.swords.loot.service.loader.domain.RawLootDefinition;
import com.morethanheroic.swords.loot.service.transformer.LootDefinitionTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class LootDefinitionLoader implements DefinitionLoader<LootDefinition> {

    private static final String LOOT_DEFINITION_LOCATION = "classpath:data/loot/definition/";
    private static final String LOOT_SCHEMA_LOCATION = "classpath:data/loot/schema.xsd";

    private final NumericXmlDefinitionLoader numericXmlDefinitionLoader;
    private final LootDefinitionTransformer lootDefinitionTransformer;

    @Override
    public List<LootDefinition> loadDefinitions() {
        return loadRawLootDefinitions().stream()
                .map(lootDefinitionTransformer::transform)
                .collect(
                        collectingAndThen(toList(), ImmutableList::copyOf)
                );
    }

    private List<RawLootDefinition> loadRawLootDefinitions() {
        return numericXmlDefinitionLoader.loadDefinitions(
                NumericDefinitionLoadingContext.<RawLootDefinition>builder()
                        .clazz(RawLootDefinition.class)
                        .resourcePath(LOOT_DEFINITION_LOCATION)
                        .schemaPath(LOOT_SCHEMA_LOCATION)
                        .build()
        );
    }
}
