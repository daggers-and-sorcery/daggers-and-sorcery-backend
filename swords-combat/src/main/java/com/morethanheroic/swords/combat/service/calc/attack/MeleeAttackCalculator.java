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

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class MeleeAttackCalculator extends GeneralAttackCalculator {

    private final CombatMessageBuilder combatMessageBuilder;
    private final DiceAttributeToDiceRollCalculationContextConverter diceAttributeToDiceRollCalculationContextConverter;
    private final DiceRollCalculator diceRollCalculator;

    public void calculateAttack(CombatEntity attacker, CombatEntity opponent, CombatResult result) {
        if (diceRollCalculator.rollDices(diceAttributeToDiceRollCalculationContextConverter.convert(attacker.getAttack())) > opponent.getDefense().getValue()) {
            dealDamage(attacker, opponent, result);

            if (opponent.getActualHealth() <= 0) {
                handleDeath(attacker, opponent, result);
            }
        } else {
            dealMiss(attacker, opponent, result);
        }
    }

    private void dealDamage(CombatEntity attacker, CombatEntity opponent, CombatResult result) {
        final int damage = diceRollCalculator.rollDices(diceAttributeToDiceRollCalculationContextConverter.convert(attacker.getDamage()));

        opponent.decreaseActualHealth(damage);

        if (attacker instanceof MonsterCombatEntity) {
            addDefenseXp(result, (UserCombatEntity) opponent, damage * 2);

            result.addMessage(combatMessageBuilder.buildDamageToPlayerMessage(attacker.getName(), damage));
        } else {
            addAttackXp(result, (UserCombatEntity) attacker, damage * 2);

            result.addMessage(combatMessageBuilder.buildDamageToMonsterMessage(opponent.getName(), damage));
        }
    }
}
