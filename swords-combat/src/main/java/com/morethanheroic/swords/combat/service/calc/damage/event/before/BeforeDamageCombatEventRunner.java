package com.morethanheroic.swords.combat.service.calc.damage.event.before;

import com.morethanheroic.swords.combat.entity.domain.CombatEntity;
import com.morethanheroic.swords.combat.service.calc.damage.event.before.domain.BeforeDamageEventResult;
import com.morethanheroic.swords.combat.service.event.damage.before.BeforeDamageCombatEventHandler;
import com.morethanheroic.swords.combat.service.event.damage.before.domain.BeforeDamageCombatEventContext;
import com.morethanheroic.swords.combat.service.event.damage.before.domain.BeforeDamageCombatEventResult;
import com.morethanheroic.swords.combat.service.event.damage.domain.DamageType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Runs the damage events.
 */
@Service
@RequiredArgsConstructor
public class BeforeDamageCombatEventRunner {

    private final List<BeforeDamageCombatEventHandler> beforeDamageCombatEventHandlers;
    private final BeforeDamageCombatEventExtractor damageCombatEventExtractor;

    /**
     * Run the events that happen when a successful attack is done and the player is about to damage the monster.
     *
     * @param attacker   the attacker who is doing the damage
     * @param opponent   the opponent who will get the damage
     * @param damageType the type of the damage
     * @return the result of the events
     */
    public BeforeDamageEventResult runEvents(final CombatEntity attacker, final CombatEntity opponent, final DamageType damageType) {
        final List<BeforeDamageCombatEventResult> eventCalculationResults = beforeDamageCombatEventHandlers.stream()
                .map(beforeDamageCombatEventHandler ->
                        beforeDamageCombatEventHandler.handleEvent(attacker, opponent,
                                BeforeDamageCombatEventContext.builder()
                                        .damageType(damageType)
                                        .build()
                        )
                )
                .flatMap(o -> o.map(Stream::of).orElseGet(Stream::empty))
                .collect(Collectors.toList());

        return BeforeDamageEventResult.builder()
                .combatSteps(damageCombatEventExtractor.extractCombatSteps(eventCalculationResults))
                .bonusDamage(damageCombatEventExtractor.extractBonusDamage(eventCalculationResults))
                .build();
    }
}
