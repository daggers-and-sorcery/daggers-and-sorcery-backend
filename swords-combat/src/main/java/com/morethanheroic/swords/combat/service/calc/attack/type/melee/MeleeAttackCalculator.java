package com.morethanheroic.swords.combat.service.calc.attack.type.melee;

import com.morethanheroic.swords.combat.domain.CombatContext;
import com.morethanheroic.swords.combat.entity.domain.CombatEntity;
import com.morethanheroic.swords.combat.entity.domain.MonsterCombatEntity;
import com.morethanheroic.swords.combat.entity.domain.UserCombatEntity;
import com.morethanheroic.swords.combat.domain.step.AttackCombatStep;
import com.morethanheroic.swords.combat.domain.step.CombatStep;
import com.morethanheroic.swords.combat.domain.step.DefaultCombatStep;
import com.morethanheroic.swords.combat.service.calc.attack.GeneralAttackCalculator;
import com.morethanheroic.swords.combat.service.calc.damage.domain.DamageCalculationResult;
import com.morethanheroic.swords.combat.service.calc.damage.type.MeleeDamageCalculator;
import com.morethanheroic.swords.combat.service.calc.death.DeathCalculator;
import com.morethanheroic.swords.combat.service.dice.DiceAttributeToDiceRollCalculationContextConverter;
import com.morethanheroic.swords.combat.service.message.CombatMessageFactory;
import com.morethanheroic.swords.combat.service.message.domain.CombatMessageContext;
import com.morethanheroic.swords.dice.service.DiceRollCalculator;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.monster.domain.MonsterType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MeleeAttackCalculator extends GeneralAttackCalculator {

    private final DiceAttributeToDiceRollCalculationContextConverter diceAttributeToDiceRollCalculationContextConverter;
    private final DiceRollCalculator diceRollCalculator;
    private final CombatMessageFactory combatMessageFactory;
    private final MeleeDamageCalculator meleeDamageCalculator;
    private final DeathCalculator deathCalculator;
    private final Random random;

    @Override
    public List<CombatStep> calculateAttack(CombatEntity attacker, CombatEntity opponent, CombatContext combatContext) {
        final List<CombatStep> result = new ArrayList<>();

        if (diceRollCalculator.rollDices(diceAttributeToDiceRollCalculationContextConverter.convert(attacker.getAttack())) > opponent.getDefense().getValue()) {
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
        final List<CombatStep> result = new ArrayList<>();

        final DamageCalculationResult damageCalculationResult = meleeDamageCalculator.calculateDamage(attacker, opponent);

        result.addAll(damageCalculationResult.getCombatSteps());

        opponent.decreaseActualHealth(damageCalculationResult.getDamage());

        if (attacker instanceof MonsterCombatEntity) {
            addDefenseXp(combatContext, damageCalculationResult.getDamage() * 2);

            final MonsterDefinition monsterDefinition = ((MonsterCombatEntity) attacker).getMonsterDefinition();
            final CombatMessageContext combatMessageContext = CombatMessageContext.builder()
                    .type(monsterDefinition.getType())
                    .subtype(monsterDefinition.getSubtype())
                    .weaponType(monsterDefinition.getWeaponType())
                    .build();

            result.add(
                    AttackCombatStep.builder()
                            .message(
                                    combatMessageFactory.newMessage(
                                            "damage_gained",
                                            "COMBAT_MESSAGE_MELEE_DAMAGE_TO_PLAYER",
                                            combatMessageContext,
                                            attacker.getName(),
                                            damageCalculationResult.getDamage(),
                                            getRandomBodySlot(combatMessageContext)
                                    )
                            )
                            .build()
            );
        } else {
            addAttackXp((UserCombatEntity) attacker, damageCalculationResult.getDamage() * 2);

            result.add(
                    AttackCombatStep.builder()
                            .message(combatMessageFactory.newMessage("damage_done", "COMBAT_MESSAGE_MELEE_DAMAGE_TO_MONSTER", opponent.getName(), damageCalculationResult.getDamage()))
                            .build()
            );
        }

        return result;
    }

    private String getRandomBodySlot(final CombatMessageContext messageContext) {
        if (messageContext.getType() == MonsterType.UNDEAD && messageContext.getSubtype() == MonsterType.SKELETON) {
            final String[] slots = new String[]{"stomach", "arm", "shoulder", "knee", "thigh"};

            return slots[random.nextInt(slots.length)];
        }

        return "";
    }

    private CombatStep dealMiss(CombatEntity attacker, CombatEntity opponent, CombatContext combatContext) {
        if (attacker instanceof MonsterCombatEntity) {
            addDefenseXp(combatContext, ((MonsterCombatEntity) attacker).getLevel() * 8);

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
