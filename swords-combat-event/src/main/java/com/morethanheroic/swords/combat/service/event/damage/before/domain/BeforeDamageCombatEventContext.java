package com.morethanheroic.swords.combat.service.event.damage.before.domain;

import com.morethanheroic.swords.combat.service.event.damage.before.BeforeDamageCombatEventHandler;
import com.morethanheroic.swords.combat.service.event.damage.domain.DamageType;
import lombok.Builder;
import lombok.Getter;

/**
 * Used in the {@link BeforeDamageCombatEventHandler}.
 */
@Getter
@Builder
public class BeforeDamageCombatEventContext {

    private final DamageType damageType;
}
