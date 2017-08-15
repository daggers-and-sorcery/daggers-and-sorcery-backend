package com.morethanheroic.swords.combat.service.calc.damage.event.type.magic;

import com.morethanheroic.swords.attribute.service.dice.DiceAttributeRoller;
import com.morethanheroic.swords.combat.service.event.damage.DamageCombatEventHandler;
import com.morethanheroic.swords.combat.service.event.damage.domain.DamageCombatEventHandlerContext;
import com.morethanheroic.swords.combat.service.event.damage.domain.DamageCombatEventHandlerResult;
import com.morethanheroic.swords.combat.service.event.damage.domain.DamageType;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Order(-100)
@Service
@RequiredArgsConstructor
public class MagicDamageCombatEventHandler implements DamageCombatEventHandler {

    private final DiceAttributeRoller diceAttributeRoller;

    @Override
    public Optional<DamageCombatEventHandlerResult> handleEvent(final DamageCombatEventHandlerContext damageCombatEventHandlerContext) {
        if (damageCombatEventHandlerContext.getDamageType() == DamageType.MAGIC) {
            final int damage = diceAttributeRoller.rollDices(damageCombatEventHandlerContext.getAttacker().getMagicDamage());

            return Optional.of(
                    DamageCombatEventHandlerResult.builder()
                            .addedDamage(damage)
                            .build()
            );
        }

        return Optional.empty();
    }
}
