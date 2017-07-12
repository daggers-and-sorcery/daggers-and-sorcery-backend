package com.morethanheroic.swords.attribute.service.calc.domain.calculation;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import lombok.Getter;
import lombok.Setter;

/**
 * Holds the result of a combat attribute calculation.
 */
@Getter
@Setter
public class DiceValueAttributeCalculationResult extends SimpleValueAttributeCalculationResult {

    private int d2;
    private int d4;
    private int d6;
    private int d8;
    private int d10;

    public DiceValueAttributeCalculationResult(final int value, final Attribute combatAttribute) {
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
    public boolean isEmpty() {
        return super.isEmpty() && d2 == 0 && d4 == 0 && d6 == 0 && d8 == 0 && d10 == 0;
    }

    @Override
    public void addCalculationResult(final SimpleValueAttributeCalculationResult attributeCalculationResult) {
        super.addCalculationResult(attributeCalculationResult);

        if (attributeCalculationResult instanceof DiceValueAttributeCalculationResult) {
            this.d2 += ((DiceValueAttributeCalculationResult) attributeCalculationResult).d2;
            this.d4 += ((DiceValueAttributeCalculationResult) attributeCalculationResult).d4;
            this.d6 += ((DiceValueAttributeCalculationResult) attributeCalculationResult).d6;
            this.d8 += ((DiceValueAttributeCalculationResult) attributeCalculationResult).d8;
            this.d10 += ((DiceValueAttributeCalculationResult) attributeCalculationResult).d10;
        }
    }
}
