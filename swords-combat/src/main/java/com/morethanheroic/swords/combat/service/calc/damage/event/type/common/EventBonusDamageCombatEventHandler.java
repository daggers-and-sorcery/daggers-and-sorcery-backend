package com.morethanheroic.swords.combat.service.calc.damage.event.type.common;

import com.morethanheroic.swords.combat.bonus.dice.CombatBonusRoller;
import com.morethanheroic.swords.combat.service.event.damage.DamageCombatEventHandler;
import com.morethanheroic.swords.combat.service.event.damage.domain.DamageCombatEventHandlerContext;
import com.morethanheroic.swords.combat.service.event.damage.domain.DamageCombatEventHandlerResult;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Order(-70)
@Service
@RequiredArgsConstructor
public class EventBonusDamageCombatEventHandler implements DamageCombatEventHandler {

    private final CombatBonusRoller combatBonusRoller;

    @Override
    public Optional<DamageCombatEventHandlerResult> handleEvent(final DamageCombatEventHandlerContext damageCombatEventHandlerContext) {
        final int damage = combatBonusRoller.rollDices(damageCombatEventHandlerContext.getDamageBonus());

        return Optional.of(
                DamageCombatEventHandlerResult.builder()
                        .addedDamage(damage)
                        .build()
        );
    }
}
