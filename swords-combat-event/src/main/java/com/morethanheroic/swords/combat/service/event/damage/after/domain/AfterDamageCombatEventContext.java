package com.morethanheroic.swords.combat.service.event.damage.after.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AfterDamageCombatEventContext {

    /**
     * Contains how much damage was done.
     */
    private final int damageDone;
}
