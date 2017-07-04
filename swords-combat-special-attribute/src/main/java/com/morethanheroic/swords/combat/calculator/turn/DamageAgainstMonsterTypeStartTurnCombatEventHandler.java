package com.morethanheroic.swords.combat.calculator.turn;

import com.google.common.collect.ImmutableMap;
import com.morethanheroic.swords.attribute.domain.SpecialAttribute;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.attribute.service.calc.domain.calculation.DiceValueAttributeCalculationResult;
import com.morethanheroic.swords.attribute.service.dice.DiceAttributeRoller;
import com.morethanheroic.swords.combat.domain.step.CombatStep;
import com.morethanheroic.swords.combat.service.event.turn.StartTurnCombatEventHandler;
import com.morethanheroic.swords.combat.service.event.turn.domain.StartTurnCombatEventContext;
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

    @Override
    public List<CombatStep> handleEvent(final StartTurnCombatEventContext startTurnCombatEventContext) {
        final MonsterDefinition monsterDefinition = startTurnCombatEventContext.getMonster().getMonsterDefinition();
        final List<CombatStep> result = new ArrayList<>();

        result.addAll(calculateDamageForTurn(startTurnCombatEventContext, monsterDefinition.getType()));
        result.addAll(calculateDamageForTurn(startTurnCombatEventContext, monsterDefinition.getSubtype()));

        return result;
    }

    private List<CombatStep> calculateDamageForTurn(final StartTurnCombatEventContext startTurnCombatEventContext, final MonsterType monsterType) {
        if (MONSTER_TYPE_SPECIAL_ATTRIBUTE_MAP.containsKey(monsterType)) {
            final DiceValueAttributeCalculationResult attributeCalculationResult = (DiceValueAttributeCalculationResult) globalAttributeCalculator.calculateActualValue(startTurnCombatEventContext.getPlayer().getUserEntity(), MONSTER_TYPE_SPECIAL_ATTRIBUTE_MAP.get(monsterType));

            startTurnCombatEventContext.getMonster().decreaseActualHealth(diceAttributeRoller.rollDices(attributeCalculationResult));
        }

        //TODO: Text!!!!!!!!!!!!!!!!!
        return Collections.emptyList();
    }
}
