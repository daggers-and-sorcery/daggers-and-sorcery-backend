package com.morethanheroic.swords.combat.service.event.damage.after.domain;

import com.morethanheroic.swords.combat.entity.domain.CombatEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AfterDamageCombatEventContext {

    private final CombatEntity attacker;
    private final CombatEntity defender;
    /**
     * Contains how much damage was done.
     */
    private final int damageDone;
}
