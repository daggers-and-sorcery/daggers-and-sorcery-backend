package com.morethanheroic.swords.attribute.service.modifier.domain;

import com.morethanheroic.swords.attribute.service.calc.domain.calculation.SimpleValueAttributeCalculationResult;

/**
 * Contains the amount of modification provided by an {@link com.morethanheroic.swords.attribute.domain.type.AttributeModifierType}.
 */
public class AttributeModifierValue {

    private int value;

    public AttributeModifierValue(int value) {
        this.value = value;
    }

    public AttributeModifierValue(SimpleValueAttributeCalculationResult attributeCalculationResult) {
        this.value = attributeCalculationResult.getValue();
    }

    public int getValue() {
        return value;
    }
}
