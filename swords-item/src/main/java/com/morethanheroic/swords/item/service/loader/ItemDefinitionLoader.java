package com.morethanheroic.swords.item.service.loader;

import com.google.common.collect.ImmutableList;
import com.morethanheroic.swords.definition.loader.DefinitionLoader;
import com.morethanheroic.swords.definition.service.loader.NumericXmlDefinitionLoader;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.service.loader.domain.RawItemDefinition;
import com.morethanheroic.swords.item.service.transformer.ItemDefinitionTransformer;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

/**
 * Loads {@link ItemDefinition}s from a data source.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ItemDefinitionLoader implements DefinitionLoader<ItemDefinition> {

    private static final String ITEM_DEFINITION_LOCATION = "classpath:data/item/definition/";
    private static final String ITEM_SCHEMA_LOCATION = "classpath:data/item/schema.xsd";
    private static final int ITEM_COUNT_TO_LOAD = 100;

    @NonNull
    private final NumericXmlDefinitionLoader numericXmlDefinitionLoader;

    @NonNull
    private final ItemDefinitionTransformer itemDefinitionTransformer;

    @Override
    public List<ItemDefinition> loadDefinitions() throws IOException {
        return loadRawItemDefinitions().stream().map(itemDefinitionTransformer::transform).collect(
                collectingAndThen(toList(), ImmutableList::copyOf)
        );
    }

    @SuppressWarnings("unchecked")
    private List<RawItemDefinition> loadRawItemDefinitions() throws IOException {
        return numericXmlDefinitionLoader.loadDefinitions(RawItemDefinition.class, ITEM_DEFINITION_LOCATION,
                ITEM_SCHEMA_LOCATION, ITEM_COUNT_TO_LOAD);
    }
}
