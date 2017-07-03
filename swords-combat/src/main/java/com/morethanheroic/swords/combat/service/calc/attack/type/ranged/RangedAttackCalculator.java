package com.morethanheroic.swords.combat.service.calc.attack.type.ranged;

import com.morethanheroic.swords.combat.domain.CombatContext;
import com.morethanheroic.swords.combat.domain.entity.CombatEntity;
import com.morethanheroic.swords.combat.domain.entity.MonsterCombatEntity;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.combat.domain.step.AttackCombatStep;
import com.morethanheroic.swords.combat.domain.step.CombatStep;
import com.morethanheroic.swords.combat.domain.step.DefaultCombatStep;
import com.morethanheroic.swords.combat.service.CombatStepListBuilder;
import com.morethanheroic.swords.combat.service.calc.attack.GeneralAttackCalculator;
import com.morethanheroic.swords.combat.service.calc.death.DeathCalculator;
import com.morethanheroic.swords.combat.service.dice.DiceAttributeToDiceRollCalculationContextConverter;
import com.morethanheroic.swords.combat.service.message.CombatMessageFactory;
import com.morethanheroic.swords.dice.service.DiceRollCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RangedAttackCalculator extends GeneralAttackCalculator {

    private final DiceAttributeToDiceRollCalculationContextConverter diceAttributeToDiceRollCalculationContextConverter;
    private final DiceRollCalculator diceRollCalculator;
    private final CombatMessageFactory combatMessageFactory;
    private final RangedDamageCalculator rangedDamageCalculator;
    private final AmmunitionLossCalculator ammunitionLossCalculator;
    private final DeathCalculator deathCalculator;

    @Override
    public List<CombatStep> calculateAttack(CombatEntity attacker, CombatEntity opponent, CombatContext combatContext) {
        final List<CombatStep> result = new ArrayList<>();

        if (diceRollCalculator.rollDices(diceAttributeToDiceRollCalculationContextConverter.convert(attacker.getAiming())) > opponent.getDefense().getValue()) {
            result.addAll(dealDamage(attacker, opponent, combatContext));

            if (opponent.getActualHealth() <= 0) {
                result.addAll(deathCalculator.handleDeath(attacker, opponent, combatContext));
            }
        } else {
            result.add(dealMiss(attacker, opponent, combatContext));
        }

        return result;
    }

    private List<CombatStep> dealDamage(CombatEntity attacker, CombatEntity opponent, CombatContext combatContext) {
        final int damage = rangedDamageCalculator.calculateDamage(attacker, opponent);

        opponent.decreaseActualHealth(damage);

        if (attacker instanceof MonsterCombatEntity) {
            return calculateMonsterDamage((MonsterCombatEntity) attacker, combatContext, damage);
        } else {
            return calculatePlayerDamage((UserCombatEntity) attacker, opponent, damage);
        }
    }

    private List<CombatStep> calculateMonsterDamage(final MonsterCombatEntity attacker, final CombatContext combatContext, final int damage) {
        addDefenseXp(combatContext, damage * 2);

        return CombatStepListBuilder.builder()
                .withCombatStep(
                        AttackCombatStep.builder()
                                .message(combatMessageFactory.newMessage("damage_gained", "COMBAT_MESSAGE_RANGED_DAMAGE_TO_PLAYER", attacker.getName(), damage))
                                .build()
                )
                .build();
    }

    private List<CombatStep> calculatePlayerDamage(final UserCombatEntity attacker, final CombatEntity opponent, final int damage) {
        addAttackXp(attacker, damage * 2);

        return CombatStepListBuilder.builder()
                .withCombatStep(
                        AttackCombatStep.builder()
                                .message(combatMessageFactory.newMessage("damage_gained", "COMBAT_MESSAGE_RANGED_DAMAGE_TO_MONSTER", opponent.getName(), damage))
                                .build()
                )
                .withCombatSteps(
                        ammunitionLossCalculator.calculateAmmunitionLoss(attacker)
                )
                .build();
    }

    private CombatStep dealMiss(CombatEntity attacker, CombatEntity opponent, CombatContext combatContext) {
        if (attacker instanceof MonsterCombatEntity) {
            addDefenseXp(combatContext, ((MonsterCombatEntity) attacker).getLevel() * 8);

            return DefaultCombatStep.builder()
                    .message(combatMessageFactory.newMessage("monster_miss", "COMBAT_MESSAGE_RANGED_MISS_BY_MONSTER", attacker.getName()))
                    .build();
        } else {
            return DefaultCombatStep.builder()
                    .message(combatMessageFactory.newMessage("player_miss", "COMBAT_MESSAGE_MELEE_MISS_BY_PLAYER", opponent.getName()))
                    .build();
        }
    }
}
