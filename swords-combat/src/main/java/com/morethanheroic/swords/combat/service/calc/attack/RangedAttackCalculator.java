package com.morethanheroic.swords.combat.service.calc.attack;

import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.domain.entity.CombatEntity;
import com.morethanheroic.swords.combat.domain.entity.MonsterCombatEntity;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.combat.service.CombatMessageBuilder;
import com.morethanheroic.swords.combat.service.DiceAttributeToDiceRollCalculationContextConverter;
import com.morethanheroic.swords.dice.service.DiceRollCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class RangedAttackCalculator extends GeneralAttackCalculator {

    private final CombatMessageBuilder combatMessageBuilder;
    private final DiceAttributeToDiceRollCalculationContextConverter diceAttributeToDiceRollCalculationContextConverter;
    private final DiceRollCalculator diceRollCalculator;
    private final Random random;

    public void calculateAttack(CombatEntity attacker, CombatEntity opponent, CombatResult result) {
        if (diceRollCalculator.rollDices(diceAttributeToDiceRollCalculationContextConverter.convert(attacker.getAiming())) > opponent.getDefense().getValue()) {
            dealDamage(attacker, opponent, result);

            if (opponent.getActualHealth() <= 0) {
                handleDeath(attacker, opponent, result);
            }
        } else {
            dealMiss(attacker, opponent, result);
        }
    }

    private void dealDamage(CombatEntity attacker, CombatEntity opponent, CombatResult result) {
        final int damage = diceRollCalculator.rollDices(diceAttributeToDiceRollCalculationContextConverter.convert(attacker.getRangedDamage()));

        opponent.decreaseActualHealth(damage);

        if (attacker instanceof MonsterCombatEntity) {
            addDefenseXp(result, (UserCombatEntity) opponent, damage * 2);

            result.addMessage(combatMessageBuilder.buildRangedDamageToPlayerMessage(attacker.getName(), damage));
        } else {
            if (random.nextInt(100) > 50) {
                result.increaseLostAmmunition();
            }

            addAttackXp(result, (UserCombatEntity) attacker, damage * 2);

            result.addMessage(combatMessageBuilder.buildRangedDamageToMonsterMessage(opponent.getName(), damage));
        }
    }
}
