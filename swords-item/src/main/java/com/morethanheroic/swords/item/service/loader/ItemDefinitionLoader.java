package com.morethanheroic.swords.item.service.loader;

import com.google.common.collect.ImmutableList;
import com.morethanheroic.swords.definition.loader.DefinitionLoader;
import com.morethanheroic.swords.definition.service.loader.NumericXmlDefinitionLoader;
import com.morethanheroic.swords.definition.service.loader.domain.NumericDefinitionLoadingContext;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.service.loader.domain.RawItemDefinition;
import com.morethanheroic.swords.item.service.transformer.ItemDefinitionTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

/**
 * Loads {@link ItemDefinition}s from a data source.
 */
@Service
@RequiredArgsConstructor
public class ItemDefinitionLoader implements DefinitionLoader<ItemDefinition> {

    private static final String ITEM_DEFINITION_LOCATION = "classpath:data/item/definition/";
    private static final String ITEM_SCHEMA_LOCATION = "classpath:data/item/schema.xsd";

    private final NumericXmlDefinitionLoader numericXmlDefinitionLoader;
    private final ItemDefinitionTransformer itemDefinitionTransformer;

    @Override
    public List<ItemDefinition> loadDefinitions() {
        return loadRawItemDefinitions().stream()
                .map(itemDefinitionTransformer::transform)
                .collect(
                        collectingAndThen(toList(), ImmutableList::copyOf)
                );
    }

    private List<RawItemDefinition> loadRawItemDefinitions() {
        return numericXmlDefinitionLoader.loadDefinitions(
                NumericDefinitionLoadingContext.<RawItemDefinition>builder()
                        .clazz(RawItemDefinition.class)
                        .resourcePath(ITEM_DEFINITION_LOCATION)
                        .schemaPath(ITEM_SCHEMA_LOCATION)
                        .build()
        );
    }
}
