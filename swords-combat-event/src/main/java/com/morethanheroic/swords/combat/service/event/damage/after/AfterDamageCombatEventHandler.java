package com.morethanheroic.swords.combat.service.event.damage.after;

import java.util.Optional;

import com.morethanheroic.swords.combat.service.event.damage.after.domain.AfterDamageCombatEventContext;
import com.morethanheroic.swords.combat.service.event.damage.after.domain.AfterDamageCombatEventResult;

public interface AfterDamageCombatEventHandler {

    Optional<AfterDamageCombatEventResult> handleEvent(final AfterDamageCombatEventContext beforeDamageCombatEventContext);
}
