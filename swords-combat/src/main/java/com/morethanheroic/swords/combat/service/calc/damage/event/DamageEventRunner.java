package com.morethanheroic.swords.combat.service.calc.damage.event;

import com.morethanheroic.swords.combat.domain.entity.CombatEntity;
import com.morethanheroic.swords.combat.service.calc.damage.event.domain.DamageEventResult;
import com.morethanheroic.swords.combat.service.event.damage.DamageCombatEventHandler;
import com.morethanheroic.swords.combat.service.event.damage.domain.DamageEventCalculationContext;
import com.morethanheroic.swords.combat.service.event.damage.domain.DamageEventCalculationResult;
import com.morethanheroic.swords.combat.service.event.damage.domain.DamageType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Runs the damage events.
 */
@Service
@RequiredArgsConstructor
public class DamageEventRunner {

    private final List<DamageCombatEventHandler> damageCombatEventHandlers;
    private final DamageEventExtractor damageEventExtractor;

    /**
     * Run the events that happen when a successful attack is done and the player is about to damage the monster.
     *
     * @param attacker   the attacker who is doing the damage
     * @param opponent   the opponent who will get the damage
     * @param damageType the type of the damage
     * @return the result of the events
     */
    public DamageEventResult runEvents(final CombatEntity attacker, final CombatEntity opponent, final DamageType damageType) {
        final List<DamageEventCalculationResult> eventCalculationResults = damageCombatEventHandlers.stream()
                .map(damageCombatEventHandler ->
                        damageCombatEventHandler.handleEvent(attacker, opponent,
                                DamageEventCalculationContext.builder()
                                        .damageType(damageType)
                                        .build()
                        )
                )
                .collect(Collectors.toList());

        return DamageEventResult.builder()
                .combatSteps(damageEventExtractor.extractCombatSteps(eventCalculationResults))
                .bonusDamage(damageEventExtractor.extractBonusDamage(eventCalculationResults))
                .build();
    }
}
