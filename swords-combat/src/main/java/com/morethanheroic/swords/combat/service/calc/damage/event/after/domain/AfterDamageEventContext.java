package com.morethanheroic.swords.combat.service.calc.damage.event.after.domain;

import com.morethanheroic.swords.combat.entity.domain.CombatEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AfterDamageEventContext {

    private final CombatEntity attacker;
    private final CombatEntity defender;
    /**
     * Contains how much damage was done.
     */
    private final int damageDone;
}
