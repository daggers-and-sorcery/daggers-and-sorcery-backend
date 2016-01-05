package com.morethanheroic.swords.attribute.service.calc.domain.calculation;

import com.morethanheroic.swords.attribute.domain.Attribute;
import lombok.Getter;
import lombok.Setter;

/**
 * Contains the result of some attribute calculations.
 */
public class AttributeCalculationResult {

    @Getter
    private final Attribute attribute;

    @Getter
    @Setter
    private int value;

    public AttributeCalculationResult(Attribute attribute) {
        this(0, attribute);
    }

    public AttributeCalculationResult(int value, Attribute attribute) {
        this.value = value;
        this.attribute = attribute;
    }

    public void increaseValue(int amount) {
        this.value += amount;
    }

    public void addCalculationResult(AttributeCalculationResult attributeCalculationResult) {
        this.value += attributeCalculationResult.value;
    }
}
