package com.morethanheroic.swords.money.service.transformer;

import com.morethanheroic.swords.money.domain.MoneyDefinition;
import com.morethanheroic.swords.money.service.loader.domain.RawMoneyDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class MoneyDefinitionTransformer {

    @Autowired
    private ConversionDefinitionTransformer conversionDefinitionTransformer;

    public MoneyDefinition transform(RawMoneyDefinition rawMoneyDefinition) {
        return MoneyDefinition.builder()
                .id(rawMoneyDefinition.getId())
                .conversionDefinitions(rawMoneyDefinition.getCombatModifiers().stream().map(conversionDefinitionTransformer::transform).collect(Collectors.toList()))
                .build();
    }
}
