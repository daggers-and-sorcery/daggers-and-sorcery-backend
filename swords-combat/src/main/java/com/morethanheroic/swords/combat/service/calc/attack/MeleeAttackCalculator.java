package com.morethanheroic.swords.combat.service.calc.attack;

import com.morethanheroic.swords.combat.domain.CombatContext;
import com.morethanheroic.swords.combat.domain.entity.CombatEntity;
import com.morethanheroic.swords.combat.domain.entity.MonsterCombatEntity;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.combat.domain.step.AttackCombatStep;
import com.morethanheroic.swords.combat.domain.step.CombatStep;
import com.morethanheroic.swords.combat.domain.step.DefaultCombatStep;
import com.morethanheroic.swords.combat.service.CombatMessageFactory;
import com.morethanheroic.swords.combat.service.dice.DiceAttributeToDiceRollCalculationContextConverter;
import com.morethanheroic.swords.dice.service.DiceRollCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MeleeAttackCalculator extends GeneralAttackCalculator {

    private final DiceAttributeToDiceRollCalculationContextConverter diceAttributeToDiceRollCalculationContextConverter;
    private final DiceRollCalculator diceRollCalculator;
    private final CombatMessageFactory combatMessageFactory;

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
            addDefenseXp((UserCombatEntity) opponent, damage * 2);

            result.add(
                    AttackCombatStep.builder()
                            .message(combatMessageFactory.newMessage("damage_gained", "COMBAT_MESSAGE_MELEE_DAMAGE_TO_PLAYER", attacker.getName(), damage))
                            .build()
            );
        } else {
            addAttackXp((UserCombatEntity) attacker, damage * 2);

            result.add(
                    AttackCombatStep.builder()
                            .message(combatMessageFactory.newMessage("damage_done", "COMBAT_MESSAGE_MELEE_DAMAGE_TO_MONSTER", opponent.getName(), damage))
                            .build()
            );
        }

        return result;
    }

    private CombatStep dealMiss(CombatEntity attacker, CombatEntity opponent, CombatContext combatContext) {
        if (attacker instanceof MonsterCombatEntity) {
            addDefenseXp((UserCombatEntity) opponent, ((MonsterCombatEntity) attacker).getLevel() * 8);

            return DefaultCombatStep.builder()
                    .message(combatMessageFactory.newMessage("monster_miss", "COMBAT_MESSAGE_MELEE_MISS_BY_MONSTER", attacker.getName()))
                    .build();
        } else {
            return DefaultCombatStep.builder()
                    .message(combatMessageFactory.newMessage("player_miss", "COMBAT_MESSAGE_MELEE_MISS_BY_PLAYER", opponent.getName()))
                    .build();
        }
    }
}
