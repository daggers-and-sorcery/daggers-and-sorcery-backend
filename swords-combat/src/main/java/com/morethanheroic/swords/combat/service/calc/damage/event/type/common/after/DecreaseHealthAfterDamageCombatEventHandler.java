package com.morethanheroic.swords.combat.service.calc.damage.event.type.common.after;

import com.morethanheroic.swords.combat.service.event.damage.DamageCombatEventHandler;
import com.morethanheroic.swords.combat.service.event.damage.domain.DamageCombatEventHandlerContext;
import com.morethanheroic.swords.combat.service.event.damage.domain.DamageCombatEventHandlerResult;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Order(300)
@Service
@RequiredArgsConstructor
public class DecreaseHealthAfterDamageCombatEventHandler implements DamageCombatEventHandler {

    @Override
    public Optional<DamageCombatEventHandlerResult> handleEvent(DamageCombatEventHandlerContext damageCombatEventHandlerContext) {
        damageCombatEventHandlerContext.getDefender().decreaseActualHealth(damageCombatEventHandlerContext.getDamageAlreadyDone());

        return Optional.empty();
    }
}
