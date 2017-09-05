package com.morethanheroic.swords.combat.service.calc.attack.type.magic;

import com.morethanheroic.swords.combat.domain.CombatContext;
import com.morethanheroic.swords.combat.entity.domain.CombatEntity;
import com.morethanheroic.swords.combat.entity.domain.MonsterCombatEntity;
import com.morethanheroic.swords.combat.entity.domain.UserCombatEntity;
import com.morethanheroic.swords.combat.service.calc.damage.DamageCalculator;
import com.morethanheroic.swords.combat.service.calc.damage.domain.DamageCalculationContext;
import com.morethanheroic.swords.combat.service.event.damage.domain.DamageType;
import com.morethanheroic.swords.combat.step.domain.CombatStep;
import com.morethanheroic.swords.combat.step.domain.DefaultCombatStep;
import com.morethanheroic.swords.combat.service.calc.attack.GeneralAttackCalculator;
import com.morethanheroic.swords.combat.service.calc.damage.domain.DamageCalculationResult;
import com.morethanheroic.swords.combat.service.calc.death.DeathCalculator;
import com.morethanheroic.swords.combat.step.message.CombatMessageFactory;
import com.morethanheroic.swords.attribute.service.dice.DiceAttributeToDiceRollCalculationContextConverter;
import com.morethanheroic.swords.dice.service.DiceRollCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MagicAttackCalculator extends GeneralAttackCalculator {

    private final DiceAttributeToDiceRollCalculationContextConverter diceAttributeToDiceRollCalculationContextConverter;
    private final DiceRollCalculator diceRollCalculator;
    private final CombatMessageFactory combatMessageFactory;
    private final DeathCalculator deathCalculator;
    private final DamageCalculator damageCalculator;

    @Override
    public List<CombatStep> calculateAttack(CombatEntity attacker, CombatEntity opponent, CombatContext combatContext) {
        final List<CombatStep> result = new ArrayList<>();

        if (diceRollCalculator.rollDices(diceAttributeToDiceRollCalculationContextConverter.convert(attacker.getMagicAttack())) > opponent.getSpellResistance().getValue()) {
            result.addAll(dealDamage(attacker, opponent, combatContext));

            if (opponent.getActualHealth() <= 0) {
                result.addAll(deathCalculator.handleDeath(attacker, opponent, combatContext));
            }
        } else {
            result.add(dealMiss(attacker, opponent, combatContext));
        }

        return result;
    }

    private List<CombatStep> dealDamage(CombatEntity attacker, CombatEntity defender, CombatContext combatContext) {
        final List<CombatStep> result = new ArrayList<>();

        final DamageCalculationResult damageCalculationResult = damageCalculator.calculateDamage(
                DamageCalculationContext.builder()
                        .attacker(attacker)
                        .defender(defender)
                        .damageType(DamageType.MAGIC)
                        .build()
        );

        result.addAll(damageCalculationResult.getCombatSteps());

        defender.decreaseActualHealth(damageCalculationResult.getDamage());

        if (attacker instanceof MonsterCombatEntity) {
            addDefenseXp(combatContext, damageCalculationResult.getDamage() * 2);

            result.add(
                    DefaultCombatStep.builder()
                            .message(combatMessageFactory.newMessage("damage_gained", "COMBAT_MESSAGE_MAGIC_DAMAGE_TO_PLAYER", attacker.getName(), damageCalculationResult.getDamage()))
                            .build()
            );
        } else {
            addAttackXp((UserCombatEntity) attacker, damageCalculationResult.getDamage() * 2);
            addOffhandXp((UserCombatEntity) attacker, damageCalculationResult.getDamage() * 2);

            result.add(
                    DefaultCombatStep.builder()
                            .message(combatMessageFactory.newMessage("damage_done", "COMBAT_MESSAGE_MAGIC_DAMAGE_TO_MONSTER", defender.getName(), damageCalculationResult.getDamage()))
                            .build()
            );
        }

        return result;
    }

    private CombatStep dealMiss(final CombatEntity attacker, final CombatEntity opponent, final CombatContext combatContext) {
        if (attacker instanceof MonsterCombatEntity) {
            addDefenseXp(combatContext, ((MonsterCombatEntity) attacker).getLevel() * 8);

            return DefaultCombatStep.builder()
                    .message(combatMessageFactory.newMessage("monster_miss", "COMBAT_MESSAGE_MAGIC_MISS_BY_MONSTER", attacker.getName()))
                    .build();
        } else {
            return DefaultCombatStep.builder()
                    .message(combatMessageFactory.newMessage("player_miss", "COMBAT_MESSAGE_MELEE_MISS_BY_PLAYER", opponent.getName()))
                    .build();
        }
    }
}
