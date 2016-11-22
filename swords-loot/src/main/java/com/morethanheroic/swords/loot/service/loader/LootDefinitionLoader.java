package com.morethanheroic.swords.loot.service.loader;

import com.google.common.collect.ImmutableList;
import com.morethanheroic.swords.definition.loader.DefinitionLoader;
import com.morethanheroic.swords.definition.service.loader.NumericXmlDefinitionLoader;
import com.morethanheroic.swords.loot.domain.LootDefinition;
import com.morethanheroic.swords.loot.service.loader.domain.RawLootDefinition;
import com.morethanheroic.swords.loot.service.transformer.LootDefinitionTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class LootDefinitionLoader implements DefinitionLoader<LootDefinition> {

    private static final String LOOT_DEFINITION_LOCATION = "classpath:data/loot/definition/";
    private static final String LOOT_SCHEMA_LOCATION = "classpath:data/loot/schema.xsd";
    private static final int LOOT_COUNT_TO_LOAD = 50;

    private final NumericXmlDefinitionLoader numericXmlDefinitionLoader;
    private final LootDefinitionTransformer lootDefinitionTransformer;

    @Override
    public List<LootDefinition> loadDefinitions() throws IOException {
        return loadRawLootDefinitions().stream().map(lootDefinitionTransformer::transform).collect(
                collectingAndThen(toList(), ImmutableList::copyOf)
        );
    }

    @SuppressWarnings("unchecked")
    private List<RawLootDefinition> loadRawLootDefinitions() throws IOException {
        return numericXmlDefinitionLoader.loadDefinitions(RawLootDefinition.class, LOOT_DEFINITION_LOCATION,
                LOOT_SCHEMA_LOCATION, LOOT_COUNT_TO_LOAD);
    }
}
