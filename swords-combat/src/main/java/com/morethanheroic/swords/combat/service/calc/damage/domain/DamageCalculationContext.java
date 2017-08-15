package com.morethanheroic.swords.combat.service.calc.damage.domain;

import com.morethanheroic.swords.combat.entity.domain.CombatEntity;
import com.morethanheroic.swords.combat.service.event.damage.domain.DamageType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DamageCalculationContext {

    private final CombatEntity attacker;
    private final CombatEntity defender;
    private final DamageType damageType;
}
