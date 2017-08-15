package com.morethanheroic.swords.combat.service.damage;

import com.google.common.collect.ImmutableMap;
import com.morethanheroic.swords.attribute.domain.SpecialAttribute;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.attribute.service.calc.domain.calculation.DiceValueAttributeCalculationResult;
import com.morethanheroic.swords.attribute.service.dice.DiceAttributeRoller;
import com.morethanheroic.swords.combat.service.event.turn.StartTurnCombatEventHandler;
import com.morethanheroic.swords.combat.service.event.turn.domain.StartTurnCombatEventContext;
import com.morethanheroic.swords.combat.step.domain.CombatStep;
import com.morethanheroic.swords.combat.step.domain.DefaultCombatStep;
import com.morethanheroic.swords.combat.step.message.CombatMessageFactory;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.monster.domain.MonsterType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DamageAgainstMonsterTypeStartTurnCombatEventHandler implements StartTurnCombatEventHandler {

    private final static Map<MonsterType, SpecialAttribute> MONSTER_TYPE_SPECIAL_ATTRIBUTE_MAP = new ImmutableMap.Builder<MonsterType, SpecialAttribute>()
            .put(MonsterType.VAMPIRE, SpecialAttribute.PERIODICAL_DAMAGE_AGAINST_VAMPIRES)
            .put(MonsterType.UNDEAD, SpecialAttribute.PERIODICAL_DAMAGE_AGAINST_UNDEAD)
            .build();

    private final GlobalAttributeCalculator globalAttributeCalculator;
    private final DiceAttributeRoller diceAttributeRoller;
    private final CombatMessageFactory combatMessageFactory;

    @Override
    public List<CombatStep> handleEvent(final StartTurnCombatEventContext startTurnCombatEventContext) {
        final MonsterDefinition monsterDefinition = startTurnCombatEventContext.getMonster().getMonsterDefinition();
        final List<CombatStep> result = new ArrayList<>();

        result.addAll(calculateDamageForTurn(startTurnCombatEventContext, monsterDefinition, monsterDefinition.getType()));
        result.addAll(calculateDamageForTurn(startTurnCombatEventContext, monsterDefinition, monsterDefinition.getSubtype()));

        return result;
    }

    private List<CombatStep> calculateDamageForTurn(final StartTurnCombatEventContext startTurnCombatEventContext, final MonsterDefinition monsterDefinition, final MonsterType monsterType) {
        if (MONSTER_TYPE_SPECIAL_ATTRIBUTE_MAP.containsKey(monsterType)) {
            final DiceValueAttributeCalculationResult attributeCalculationResult = (DiceValueAttributeCalculationResult) globalAttributeCalculator.calculateActualValue(startTurnCombatEventContext.getPlayer().getUserEntity(), MONSTER_TYPE_SPECIAL_ATTRIBUTE_MAP.get(monsterType));

            if (!attributeCalculationResult.isEmpty()) {
                final List<CombatStep> result = new ArrayList<>();

                final int damage = diceAttributeRoller.rollDices(attributeCalculationResult);
                startTurnCombatEventContext.getMonster().decreaseActualHealth(damage);

                result.add(
                        DefaultCombatStep.builder()
                                .message(combatMessageFactory.newMessage("turn_start_dmg_for_monster_type", "COMBAT_MESSAGE_DAMAGE_ON_TURN_START_FOR_MONSTER_WITH_TYPE", damage, monsterDefinition.getName(), monsterType.getName()))
                                .build()
                );

                return result;
            }
        }

        return Collections.emptyList();
    }
}
