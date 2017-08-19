package com.morethanheroic.swords.combat.service.calc.damage.event.after;

import com.morethanheroic.swords.combat.service.calc.damage.event.after.domain.AfterDamageEventContext;
import com.morethanheroic.swords.combat.service.calc.damage.event.after.domain.AfterDamageEventResult;
import com.morethanheroic.swords.combat.service.event.damage.after.AfterDamageCombatEventHandler;
import com.morethanheroic.swords.combat.service.event.damage.after.domain.AfterDamageCombatEventContext;
import com.morethanheroic.swords.combat.step.domain.CombatStep;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AfterDamageCombatEventRunner {

    private final List<AfterDamageCombatEventHandler> afterDamageCombatEventHandlers;

    public AfterDamageEventResult runEvents(final AfterDamageEventContext afterDamageEventContext) {
        final List<CombatStep> combatSteps = afterDamageCombatEventHandlers.stream()
                .map(afterDamageCombatEventHandler -> afterDamageCombatEventHandler.handleEvent(
                        AfterDamageCombatEventContext.builder()
                                .attacker(afterDamageEventContext.getAttacker())
                                .defender(afterDamageEventContext.getDefender())
                                .damageDone(afterDamageEventContext.getDamageDone())
                                .build()
                        )
                )
                .filter(afterDamageCombatEventResult -> afterDamageCombatEventResult.isPresent())
                .map(afterDamageCombatEventResult -> afterDamageCombatEventResult.get().getCombatSteps())
                .flatMap(combatSteps1 -> combatSteps1.stream())
                .collect(Collectors.toList());

        return AfterDamageEventResult.builder()
                .combatSteps(combatSteps)
                .build();
    }
}
