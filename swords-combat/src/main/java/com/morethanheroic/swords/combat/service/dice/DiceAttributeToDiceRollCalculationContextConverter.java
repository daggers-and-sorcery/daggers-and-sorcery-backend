package com.morethanheroic.swords.combat.service.dice;

import com.morethanheroic.swords.attribute.domain.DiceAttribute;
import com.morethanheroic.swords.combat.bonus.domain.CombatBonus;
import com.morethanheroic.swords.dice.domain.DiceRollCalculationContext;
import org.springframework.stereotype.Service;

@Service
public class DiceAttributeToDiceRollCalculationContextConverter {

    /**
     * @deprecated Don't direct convert an {@link DiceAttribute} to a {@link DiceRollCalculationContext}. Use {@link CombatBonus} instead.
     * There are however no method yet that would do this to you.
     */
    @Deprecated
    public DiceRollCalculationContext convert(final DiceAttribute diceAttribute) {
        return DiceRollCalculationContext.builder()
                .value(diceAttribute.getValue())
                .d2(diceAttribute.getD2())
                .d4(diceAttribute.getD4())
                .d6(diceAttribute.getD6())
                .d8(diceAttribute.getD8())
                .d10(diceAttribute.getD10())
                .build();
    }
}
