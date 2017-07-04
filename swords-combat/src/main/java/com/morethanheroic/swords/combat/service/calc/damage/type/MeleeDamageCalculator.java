package com.morethanheroic.swords.combat.service.calc.damage.type;

import com.morethanheroic.swords.combat.entity.domain.CombatEntity;
import com.morethanheroic.swords.combat.service.calc.damage.DamageCalculator;
import com.morethanheroic.swords.combat.service.calc.damage.domain.DamageCalculationResult;
import com.morethanheroic.swords.combat.service.calc.damage.event.DamageCombatEventRunner;
import com.morethanheroic.swords.combat.service.calc.damage.event.domain.DamageEventResult;
import com.morethanheroic.swords.combat.bonus.dice.CombatBonusRoller;
import com.morethanheroic.swords.attribute.service.dice.DiceAttributeRoller;
import com.morethanheroic.swords.combat.service.event.damage.domain.DamageType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MeleeDamageCalculator implements DamageCalculator {

    private final DiceAttributeRoller diceAttributeRoller;
    private final DamageCombatEventRunner damageCombatEventRunner;
    private final CombatBonusRoller combatBonusRoller;

    public DamageCalculationResult calculateDamage(final CombatEntity attacker, final CombatEntity opponent) {
        final DamageEventResult damageEventResult = damageCombatEventRunner.runEvents(attacker, opponent, DamageType.MELEE);

        return DamageCalculationResult.builder()
                .damage(calculateFinalDamage(attacker, opponent, damageEventResult))
                .combatSteps(damageEventResult.getCombatSteps())
                .build();
    }

    private int calculateFinalDamage(final CombatEntity attacker, final CombatEntity opponent, final DamageEventResult damageEventResult) {
        final int resultDamage = calculateBaseDamage(attacker) + combatBonusRoller.rollDices(damageEventResult.getBonusDamage()) - calculateDamageReduction(opponent);

        return resultDamage > 1 ? resultDamage : 1;
    }

    private int calculateBaseDamage(final CombatEntity attacker) {
        return diceAttributeRoller.rollDices(attacker.getDamage());
    }

    private int calculateDamageReduction(final CombatEntity opponent) {
        return diceAttributeRoller.rollDices(opponent.getDamageReduction());
    }
}
