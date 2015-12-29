package com.morethanheroic.swords.attribute.service.calc.domain;

import com.morethanheroic.swords.attribute.domain.Attribute;

/**
 * Contains the result of some attribute calculations.
 */
public class AttributeCalculationResult {

    private Attribute attribute;
    private int value;

    public AttributeCalculationResult(Attribute attribute) {
        this(0, attribute);
    }

    public AttributeCalculationResult(int value, Attribute attribute) {
        this.value = value;
        this.attribute = attribute;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void increaseValue(int amount) {
        this.value += amount;
    }

    public void addCalculationResult(AttributeCalculationResult attributeCalculationResult) {
        this.value += attributeCalculationResult.value;
    }

    public Attribute getAttribute() {
        return attribute;
    }
}
