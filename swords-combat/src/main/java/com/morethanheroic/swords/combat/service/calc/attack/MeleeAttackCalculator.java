package com.morethanheroic.swords.combat.service.calc.attack;

import com.morethanheroic.swords.combat.domain.CombatContext;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.domain.entity.CombatEntity;
import com.morethanheroic.swords.combat.domain.entity.MonsterCombatEntity;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.combat.domain.step.AttackCombatStep;
import com.morethanheroic.swords.combat.domain.step.CombatStep;
import com.morethanheroic.swords.combat.service.CombatMessageBuilder;
import com.morethanheroic.swords.combat.service.DiceAttributeToDiceRollCalculationContextConverter;
import com.morethanheroic.swords.dice.service.DiceRollCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class MeleeAttackCalculator extends GeneralAttackCalculator {

    private final CombatMessageBuilder combatMessageBuilder;
    private final DiceAttributeToDiceRollCalculationContextConverter diceAttributeToDiceRollCalculationContextConverter;
    private final DiceRollCalculator diceRollCalculator;

    @Override
    public List<CombatStep> calculateAttack(CombatEntity attacker, CombatEntity opponent, CombatContext combatContext) {
        final List<CombatStep> result = new ArrayList<>();

        if (diceRollCalculator.rollDices(diceAttributeToDiceRollCalculationContextConverter.convert(attacker.getAttack())) > opponent.getDefense().getValue()) {
            result.addAll(dealDamage(attacker, opponent, combatContext));

            if (opponent.getActualHealth() <= 0) {
                result.add(handleDeath(attacker, opponent, combatContext));
            }
        } else {
            result.add(dealMiss(attacker, opponent, combatContext));
        }

        return result;
    }

    private List<CombatStep> dealDamage(CombatEntity attacker, CombatEntity opponent, CombatContext combatContext) {
        final List<CombatStep> result = new ArrayList<>();

        final int damage = diceRollCalculator.rollDices(diceAttributeToDiceRollCalculationContextConverter.convert(attacker.getDamage()));

        opponent.decreaseActualHealth(damage);

        if (attacker instanceof MonsterCombatEntity) {
            addDefenseXp(combatContext, (UserCombatEntity) opponent, damage * 2);

            result.add(
                    AttackCombatStep.builder()
                            .message(combatMessageBuilder.buildDamageToPlayerMessage(attacker.getName(), damage))
                            .build()
            );
        } else {
            addAttackXp(combatContext, (UserCombatEntity) attacker, damage * 2);

            result.add(
                    AttackCombatStep.builder()
                            .message(combatMessageBuilder.buildDamageToMonsterMessage(opponent.getName(), damage))
                            .build()
            );
        }

        return result;
    }

    @Deprecated
    @Override
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

    @Deprecated
    private void dealDamage(CombatEntity attacker, CombatEntity opponent, CombatResult result) {
        int damage = diceRollCalculator.rollDices(diceAttributeToDiceRollCalculationContextConverter.convert(attacker.getDamage()));

        if (damage > opponent.getActualHealth()) {
            damage = opponent.getActualHealth();
        }

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
