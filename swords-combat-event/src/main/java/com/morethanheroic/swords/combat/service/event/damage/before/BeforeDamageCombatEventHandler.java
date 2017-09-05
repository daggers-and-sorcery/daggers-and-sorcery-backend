package com.morethanheroic.swords.combat.service.event.damage.before;

import com.morethanheroic.swords.combat.entity.domain.CombatEntity;
import com.morethanheroic.swords.combat.service.event.damage.before.domain.BeforeDamageCombatEventContext;
import com.morethanheroic.swords.combat.service.event.damage.before.domain.BeforeDamageCombatEventResult;

import java.util.Optional;

/**
 * This event is fired before damage is being done between two combat entities.
 */
public interface BeforeDamageCombatEventHandler {

    Optional<BeforeDamageCombatEventResult> handleEvent(final CombatEntity damagingEntity, final CombatEntity damagedEntity, final BeforeDamageCombatEventContext beforeDamageCombatEventContext);
}
