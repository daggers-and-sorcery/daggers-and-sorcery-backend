package com.morethanheroic.swords.attribute.service.modifier.domain;

import com.morethanheroic.swords.attribute.service.calc.domain.calculation.CombatAttributeCalculationResult;
import lombok.Getter;

@Getter
public class CombatAttributeModifierValue extends AttributeModifierValue {

    private int d2;
    private int d4;
    private int d6;
    private int d8;
    private int d10;

    public CombatAttributeModifierValue(int value) {
        super(value);
    }

    public CombatAttributeModifierValue(CombatAttributeCalculationResult attributeCalculationResult) {
        super(attributeCalculationResult);

        this.d2 = attributeCalculationResult.getD2();
        this.d4 = attributeCalculationResult.getD4();
        this.d6 = attributeCalculationResult.getD6();
        this.d8 = attributeCalculationResult.getD8();
        this.d10 = attributeCalculationResult.getD10();
    }
}
