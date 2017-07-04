package com.morethanheroic.swords.combat.service.dice;

import com.morethanheroic.swords.combat.bonus.domain.CombatBonus;
import com.morethanheroic.swords.dice.domain.DiceRollCalculationContext;
import com.morethanheroic.swords.dice.service.DiceRollCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CombatBonusRoller {

    private final DiceRollCalculator diceRollCalculator;

    public int rollDices(final CombatBonus combatBonus) {
        return diceRollCalculator.rollDices(createContextFor(combatBonus));
    }

    private DiceRollCalculationContext createContextFor(final CombatBonus combatBonus) {
        return DiceRollCalculationContext.builder()
                .value(combatBonus.getValue())
                .d2(combatBonus.getD2())
                .d4(combatBonus.getD4())
                .d6(combatBonus.getD6())
                .d8(combatBonus.getD8())
                .d10(combatBonus.getD10())
                .build();
    }
}
