package com.morethanheroic.swords.money.service.loader;

import com.google.common.collect.ImmutableList;
import com.morethanheroic.definition.loader.DefinitionLoader;
import com.morethanheroic.swords.money.domain.MoneyDefinition;
import com.morethanheroic.swords.money.domain.MoneyType;
import com.morethanheroic.swords.money.service.loader.domain.RawMoneyDefinition;
import com.morethanheroic.swords.money.service.transformer.MoneyDefinitionTransformer;
import com.morethanheroic.xml.service.loader.EnumXmlDefinitionLoader;
import com.morethanheroic.xml.service.loader.domain.EnumDefinitionLoadingContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

/**
 * Loads the {@link MoneyDefinition}s from xml files.
 */
@Service
@RequiredArgsConstructor
public class MoneyDefinitionLoader implements DefinitionLoader<MoneyDefinition> {

    private static final String MONEY_DEFINITION_LOCATION = "classpath:data/money/definition/";
    private static final String MONEY_SCHEMA_LOCATION = "classpath:data/money/schema.xsd";

    private final EnumXmlDefinitionLoader enumXmlDefinitionLoader;
    private final MoneyDefinitionTransformer moneyDefinitionTransformer;

    @Override
    public List<MoneyDefinition> loadDefinitions() {
        return loadRawMoneyDefinitions().stream()
                .map(moneyDefinitionTransformer::transform)
                .collect(
                        collectingAndThen(toList(), ImmutableList::copyOf)
                );
    }

    private List<RawMoneyDefinition> loadRawMoneyDefinitions() {
        return enumXmlDefinitionLoader.loadDefinitions(
                EnumDefinitionLoadingContext.<RawMoneyDefinition>builder()
                        .clazz(RawMoneyDefinition.class)
                        .resourcePath(MONEY_DEFINITION_LOCATION)
                        .schemaPath(MONEY_SCHEMA_LOCATION)
                        .target(MoneyType.class)
                        .build()
        );
    }
}
