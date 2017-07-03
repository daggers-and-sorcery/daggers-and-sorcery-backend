package com.morethanheroic.swords.combat.service.event.damage;

import com.morethanheroic.swords.combat.domain.entity.CombatEntity;

/**
 * This event is fired when a damage is being done between two combat entities.
 */
public interface DamageCombatEventHandler {

    DamageEventCalculationResult handleEvent(final CombatEntity damagingEntity, final CombatEntity damagedEntity, final DamageEventCalculationContext damageEventCalculationContext);
}
