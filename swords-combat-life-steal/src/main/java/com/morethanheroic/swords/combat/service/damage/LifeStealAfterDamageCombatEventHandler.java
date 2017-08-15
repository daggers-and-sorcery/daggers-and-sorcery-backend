package com.morethanheroic.swords.combat.service.damage;

import java.util.Optional;

import com.morethanheroic.swords.combat.service.event.damage.after.AfterDamageCombatEventHandler;
import com.morethanheroic.swords.combat.service.event.damage.after.domain.AfterDamageCombatEventContext;
import com.morethanheroic.swords.combat.service.event.damage.after.domain.AfterDamageCombatEventResult;
import org.springframework.stereotype.Component;

@Component
public class LifeStealAfterDamageCombatEventHandler implements AfterDamageCombatEventHandler {

    @Override
    public Optional<AfterDamageCombatEventResult> handleEvent(AfterDamageCombatEventContext beforeDamageCombatEventContext) {
        //TODO!
        return Optional.empty();
    }
}
