package com.morethanheroic.swords.combat.service.calc.attack;

import com.morethanheroic.swords.combat.domain.CombatContext;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.domain.entity.CombatEntity;
import com.morethanheroic.swords.combat.domain.entity.MonsterCombatEntity;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.combat.domain.step.AttackCombatStep;
import com.morethanheroic.swords.combat.domain.step.CombatStep;
import com.morethanheroic.swords.combat.service.CombatMessageBuilder;
import com.morethanheroic.swords.combat.service.CombatMessageFactory;
import com.morethanheroic.swords.combat.service.DiceAttributeToDiceRollCalculationContextConverter;
import com.morethanheroic.swords.dice.service.DiceRollCalculator;
import com.morethanheroic.swords.equipment.service.EquipmentFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class RangedAttackCalculator extends GeneralAttackCalculator {

    private final CombatMessageBuilder combatMessageBuilder;
    private final DiceAttributeToDiceRollCalculationContextConverter diceAttributeToDiceRollCalculationContextConverter;
    private final DiceRollCalculator diceRollCalculator;
    private final EquipmentFacade equipmentFacade;
    private final CombatMessageFactory combatMessageFactory;
    private final Random random;

    @Override
    public List<CombatStep> calculateAttack(CombatEntity attacker, CombatEntity opponent, CombatContext combatContext) {
        final List<CombatStep> result = new ArrayList<>();

        if (diceRollCalculator.rollDices(diceAttributeToDiceRollCalculationContextConverter.convert(attacker.getAiming())) > opponent.getDefense().getValue()) {
            result.addAll(dealDamage(attacker, opponent, combatContext));

            if (opponent.getActualHealth() <= 0) {
                result.add(handleDeath(attacker, opponent, combatContext));
            }
        } else {
            result.add(dealMiss(attacker, opponent, combatContext));
        }

        return result;
    }

    @Deprecated
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

    private List<CombatStep> dealDamage(CombatEntity attacker, CombatEntity opponent, CombatContext combatContext) {
        final List<CombatStep> result = new ArrayList<>();

        final int damage = diceRollCalculator.rollDices(diceAttributeToDiceRollCalculationContextConverter.convert(attacker.getRangedDamage()));

        opponent.decreaseActualHealth(damage);

        if (attacker instanceof MonsterCombatEntity) {
            addDefenseXp(combatContext, (UserCombatEntity) opponent, damage * 2);

            result.add(
                    AttackCombatStep.builder()
                            .message(combatMessageBuilder.buildRangedDamageToPlayerMessage(attacker.getName(), damage))
                            .build()
            );
        } else {
            if (random.nextInt(100) > 75) {
                equipmentFacade.getEquipment(((UserCombatEntity) attacker).getUserEntity()).decreaseAmmunition(1);

                result.add(
                        AttackCombatStep.builder()
                                .message(combatMessageFactory.newMessage("ammunition", "COMBAT_MESSAGE_ARROW_LOST", 1))
                                .build()
                );
            }

            addAttackXp(combatContext, (UserCombatEntity) attacker, damage * 2);

            result.add(
                    AttackCombatStep.builder()
                            .message(combatMessageBuilder.buildRangedDamageToMonsterMessage(attacker.getName(), damage))
                            .build()
            );
        }

        return result;
    }

    @Deprecated
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
