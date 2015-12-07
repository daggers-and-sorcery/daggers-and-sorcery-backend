package com.morethanheroic.swords.money.service.transformer;

import com.morethanheroic.swords.definition.transformer.DefinitionTransformer;
import com.morethanheroic.swords.money.domain.ConversionDefinition;
import com.morethanheroic.swords.money.domain.MoneyDefinition;
import com.morethanheroic.swords.money.service.loader.domain.RawConversionDefinition;
import com.morethanheroic.swords.money.service.loader.domain.RawMoneyDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Transform {@link RawMoneyDefinition} to their domain object pair {@link MoneyDefinition}.
 */
@Service
public class MoneyDefinitionTransformer implements DefinitionTransformer<MoneyDefinition, RawMoneyDefinition> {

    @Autowired
    private ConversionDefinitionTransformer conversionDefinitionTransformer;

    public MoneyDefinition transform(RawMoneyDefinition rawMoneyDefinition) {
        return MoneyDefinition.builder()
                .id(rawMoneyDefinition.getId())
                .conversionDefinitions(convertListOfConversionDefinitions(rawMoneyDefinition.getCombatModifiers()))
                .build();
    }

    private List<ConversionDefinition> convertListOfConversionDefinitions(List<RawConversionDefinition> rawConversionDefinitions) {
        return rawConversionDefinitions.stream().map(conversionDefinitionTransformer::transform).collect(Collectors.toList());
    }
}
