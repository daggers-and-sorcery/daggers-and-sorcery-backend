package com.morethanheroic.swords.statuseffect.service.attribute.custom;

import com.morethanheroic.swords.statuseffect.service.attribute.custom.domain.CustomModifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CustomStatusEffectAttributeModifierCalculatorProvider {

    private final Map<CustomModifier, CustomStatusEffectAttributeModifierCalculator> calculatorMap;

    public CustomStatusEffectAttributeModifierCalculatorProvider(final List<CustomStatusEffectAttributeModifierCalculator> customStatusEffectAttributeModifierCalculators) {
        calculatorMap = customStatusEffectAttributeModifierCalculators.stream()
                .collect(Collectors.toMap(CustomStatusEffectAttributeModifierCalculator::supportedEffectId, Function.identity()));
    }

    public CustomStatusEffectAttributeModifierCalculator getCalculator(final CustomModifier customModifier) {
        return calculatorMap.get(customModifier);
    }
}
