package com.morethanheroic.swords.combat.service.calc.damage.event.type.common;

import com.morethanheroic.swords.combat.service.event.damage.DamageCombatEventHandler;
import com.morethanheroic.swords.combat.service.event.damage.domain.DamageCombatEventHandlerContext;
import com.morethanheroic.swords.combat.service.event.damage.domain.DamageCombatEventHandlerResult;
import lombok.RequiredArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Service
@RequiredArgsConstructor
public class MinimumDamageCombatEventHandler implements DamageCombatEventHandler {

    @Override
    public Optional<DamageCombatEventHandlerResult> handleEvent(DamageCombatEventHandlerContext damageCombatEventHandlerContext) {
        if (damageCombatEventHandlerContext.getDamageAlreadyDone() <= 0) {
            return Optional.of(
                    DamageCombatEventHandlerResult.builder()
                            .addedDamage(-damageCombatEventHandlerContext.getDamageAlreadyDone() + 1)
                            .build()
            );
        }

        return Optional.empty();
    }
}
