package com.morethanheroic.swords.money.service.loader;

import com.morethanheroic.swords.definition.loader.DefinitionLoader;
import com.morethanheroic.swords.definition.service.loader.EnumXmlDefinitionLoader;
import com.morethanheroic.swords.money.domain.Money;
import com.morethanheroic.swords.money.domain.MoneyDefinition;
import com.morethanheroic.swords.money.service.loader.domain.RawMoneyDefinition;
import com.morethanheroic.swords.money.service.transformer.MoneyDefinitionTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Loads the {@link MoneyDefinition}s from xml files.
 */
@Service
public class MoneyDefinitionLoader implements DefinitionLoader<MoneyDefinition> {

    private static final String MONEY_DEFINITION_LOCATION = "classpath:data/money/definition/";
    private static final String MONEY_SCHEMA_LOCATION = "classpath:data/money/schema.xsd";

    @Autowired
    private EnumXmlDefinitionLoader enumXmlDefinitionLoader;

    @Autowired
    private MoneyDefinitionTransformer moneyDefinitionTransformer;

    @Override
    public List<MoneyDefinition> loadDefinitions() throws IOException {
        return loadRawMoneyDefinitions().stream().map(moneyDefinitionTransformer::transform).collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
    public List<RawMoneyDefinition> loadRawMoneyDefinitions() throws IOException {
        return enumXmlDefinitionLoader.loadDefinitions(RawMoneyDefinition.class, MONEY_DEFINITION_LOCATION, MONEY_SCHEMA_LOCATION, Money.class);
    }
}
