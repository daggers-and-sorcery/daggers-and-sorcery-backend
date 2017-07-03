package com.morethanheroic.swords.combat.service.calc.damage.event;

import com.morethanheroic.swords.combat.domain.entity.CombatEntity;
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

    public List<DamageEventCalculationResult> runEvents(final CombatEntity attacker, final CombatEntity opponent, final DamageType damageType) {
        return damageCombatEventHandlers.stream()
                .map(damageCombatEventHandler ->
                        damageCombatEventHandler.handleEvent(attacker, opponent,
                                DamageEventCalculationContext.builder()
                                        .damageType(damageType)
                                        .build()
                        )
                )
                .collect(Collectors.toList());
    }
}
