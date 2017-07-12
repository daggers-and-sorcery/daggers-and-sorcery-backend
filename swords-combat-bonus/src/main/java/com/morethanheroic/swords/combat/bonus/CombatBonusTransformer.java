package com.morethanheroic.swords.combat.bonus;

import com.morethanheroic.swords.attribute.service.calc.domain.calculation.DiceValueAttributeCalculationResult;
import com.morethanheroic.swords.combat.bonus.domain.CombatBonus;
import org.springframework.stereotype.Service;

/**
 * Transform from and to {@link CombatBonus}.
 */
@Service
public class CombatBonusTransformer {

    public CombatBonus createFrom(final DiceValueAttributeCalculationResult diceValueAttributeCalculationResult) {
        return CombatBonus.builder()
                .value(diceValueAttributeCalculationResult.getValue())
                .d2(diceValueAttributeCalculationResult.getD2())
                .d4(diceValueAttributeCalculationResult.getD4())
                .d6(diceValueAttributeCalculationResult.getD6())
                .d8(diceValueAttributeCalculationResult.getD8())
                .d10(diceValueAttributeCalculationResult.getD10())
                .build();
    }
}
