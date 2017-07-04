package com.morethanheroic.swords.attribute.service.calc.domain.calculation;

import com.morethanheroic.swords.attribute.domain.Attribute;
import lombok.Getter;
import lombok.Setter;

/**
 * Contains the result of some attribute calculations.
 */
public class SimpleValueAttributeCalculationResult {

    @Getter
    private final Attribute attribute;

    @Getter
    @Setter
    private int value;

    public SimpleValueAttributeCalculationResult(Attribute attribute) {
        this(0, attribute);
    }

    public SimpleValueAttributeCalculationResult(int value, Attribute attribute) {
        this.value = value;
        this.attribute = attribute;
    }

    public void increaseValue(int amount) {
        this.value += amount;
    }

    public void addCalculationResult(SimpleValueAttributeCalculationResult attributeCalculationResult) {
        this.value += attributeCalculationResult.value;
    }
}
