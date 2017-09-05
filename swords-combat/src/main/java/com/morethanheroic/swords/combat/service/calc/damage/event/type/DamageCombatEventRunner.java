package com.morethanheroic.swords.combat.service.calc.damage.event.type;

import com.morethanheroic.swords.combat.service.calc.damage.event.type.domain.DamageCombatEventRunnerContext;
import com.morethanheroic.swords.combat.service.calc.damage.event.type.domain.DamageCombatEventRunnerResult;
import com.morethanheroic.swords.combat.service.event.damage.DamageCombatEventHandler;
import com.morethanheroic.swords.combat.service.event.damage.domain.DamageCombatEventHandlerContext;
import com.morethanheroic.swords.combat.service.event.damage.domain.DamageCombatEventHandlerResult;
import com.morethanheroic.swords.combat.step.domain.CombatStep;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DamageCombatEventRunner {

    private final List<DamageCombatEventHandler> damageCombatEventHandlers;

    public DamageCombatEventRunnerResult runEvents(final DamageCombatEventRunnerContext damageCombatEventRunnerContext) {
        final List<CombatStep> combatSteps = new ArrayList<>();
        int damageAlreadyDone = 0;

        for (DamageCombatEventHandler damageCombatEventHandler : damageCombatEventHandlers) {
            Optional<DamageCombatEventHandlerResult> optionalDamageCombatEventHandlerResult = damageCombatEventHandler.handleEvent(
                    DamageCombatEventHandlerContext.builder()
                            .attacker(damageCombatEventRunnerContext.getAttacker())
                            .defender(damageCombatEventRunnerContext.getDefender())
                            .damageType(damageCombatEventRunnerContext.getDamageType())
                            .damageBonus(damageCombatEventRunnerContext.getDamageBonus())
                            .damageAlreadyDone(damageAlreadyDone)
                            .build()
            );

            if (optionalDamageCombatEventHandlerResult.isPresent()) {
                final DamageCombatEventHandlerResult damageCombatEventHandlerResult = optionalDamageCombatEventHandlerResult.get();

                damageAlreadyDone += damageCombatEventHandlerResult.getAddedDamage();

                combatSteps.addAll(damageCombatEventHandlerResult.getCombatSteps());
            }
        }

        return DamageCombatEventRunnerResult.builder()
                .combatSteps(combatSteps)
                .damage(damageAlreadyDone)
                .build();
    }
}
