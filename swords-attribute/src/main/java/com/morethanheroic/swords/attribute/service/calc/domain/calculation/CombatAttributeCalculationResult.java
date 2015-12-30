package com.morethanheroic.swords.attribute.service.calc.domain.calculation;

import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import lombok.Getter;
import lombok.Setter;

/**
 * Holds the result of a combat attribute calculation.
 */
@Getter
@Setter
public class CombatAttributeCalculationResult extends AttributeCalculationResult {

    private int d2;
    private int d4;
    private int d6;
    private int d8;
    private int d10;

    public CombatAttributeCalculationResult(CombatAttribute combatAttribute) {
        super(combatAttribute);
    }

    public CombatAttributeCalculationResult(int value, CombatAttribute combatAttribute) {
        super(value, combatAttribute);
    }

    public void increaseD2(int amount) {
        this.d2 += amount;
    }

    public void increaseD4(int amount) {
        this.d4 += amount;
    }

    public void increaseD6(int amount) {
        this.d6 += amount;
    }

    public void increaseD8(int amount) {
        this.d8 += amount;
    }

    public void increaseD10(int amount) {
        this.d10 += amount;
    }

    @Override
    public void addCalculationResult(AttributeCalculationResult attributeCalculationResult) {
        super.addCalculationResult(attributeCalculationResult);

        if (attributeCalculationResult instanceof CombatAttributeCalculationResult) {
            this.d2 += ((CombatAttributeCalculationResult) attributeCalculationResult).d2;
            this.d4 += ((CombatAttributeCalculationResult) attributeCalculationResult).d4;
            this.d6 += ((CombatAttributeCalculationResult) attributeCalculationResult).d6;
            this.d8 += ((CombatAttributeCalculationResult) attributeCalculationResult).d8;
            this.d10 += ((CombatAttributeCalculationResult) attributeCalculationResult).d10;
        }
    }
}
