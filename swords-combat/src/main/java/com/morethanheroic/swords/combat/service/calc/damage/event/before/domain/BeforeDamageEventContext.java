package com.morethanheroic.swords.combat.service.calc.damage.event.before.domain;

import com.morethanheroic.swords.combat.entity.domain.CombatEntity;
import com.morethanheroic.swords.combat.service.event.damage.domain.DamageType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BeforeDamageEventContext {

    private final CombatEntity attacker;
    private final CombatEntity defender;
    private final DamageType damageType;
}
