package com.morethanheroic.swords.attribute.service.modifier.domain;

import com.morethanheroic.swords.attribute.service.calc.domain.AttributeCalculationResult;

/**
 * Contains the amount of modification provided by an {@link com.morethanheroic.swords.attribute.domain.type.AttributeModifierType}.
 */
public class AttributeModifierValue {

    private int value = 0;
    private int d2 = 0;
    private int d4 = 0;
    private int d6 = 0;
    private int d8 = 0;
    private int d10 = 0;

    public AttributeModifierValue(int value) {
        this.value = value;
    }

    public AttributeModifierValue(AttributeCalculationResult attributeCalculationResult) {
        this.value = attributeCalculationResult.getValue();
        this.d2 = attributeCalculationResult.getD2();
        this.d4 = attributeCalculationResult.getD4();
        this.d6 = attributeCalculationResult.getD6();
        this.d8 = attributeCalculationResult.getD8();
        this.d10 = attributeCalculationResult.getD10();
    }

    public int getValue() {
        return value;
    }

    public int getD2() {
        return d2;
    }

    public int getD4() {
        return d4;
    }

    public int getD6() {
        return d6;
    }

    public int getD8() {
        return d8;
    }

    public int getD10() {
        return d10;
    }
}
