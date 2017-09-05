package com.morethanheroic.swords.combat.service.calc.damage.event.after.domain;

import com.morethanheroic.swords.combat.entity.domain.CombatEntity;
import com.morethanheroic.swords.combat.service.event.damage.domain.DamageType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AfterDamageEventContext {

    private final CombatEntity attacker;
    private final CombatEntity defender;
    private final DamageType damageType;
    /**
     * Contains how much damage was done.
     */
    private final int damageDone;
}
