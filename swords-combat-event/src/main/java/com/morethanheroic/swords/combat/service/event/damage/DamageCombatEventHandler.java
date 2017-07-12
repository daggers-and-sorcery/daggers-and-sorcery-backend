package com.morethanheroic.swords.combat.service.event.damage;

import com.morethanheroic.swords.combat.entity.domain.CombatEntity;
import com.morethanheroic.swords.combat.service.event.damage.domain.DamageCombatEventContext;
import com.morethanheroic.swords.combat.service.event.damage.domain.DamageCombatEventResult;

/**
 * This event is fired when a damage is being done between two combat entities.
 */
public interface DamageCombatEventHandler {

    DamageCombatEventResult handleEvent(final CombatEntity damagingEntity, final CombatEntity damagedEntity, final DamageCombatEventContext damageCombatEventContext);
}
