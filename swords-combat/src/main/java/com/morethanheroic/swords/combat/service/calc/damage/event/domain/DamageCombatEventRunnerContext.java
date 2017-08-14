package com.morethanheroic.swords.combat.service.calc.damage.event.domain;

import com.morethanheroic.swords.combat.bonus.domain.CombatBonus;
import com.morethanheroic.swords.combat.entity.domain.CombatEntity;
import com.morethanheroic.swords.combat.service.event.damage.domain.DamageType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DamageCombatEventRunnerContext {

    private final CombatEntity attacker;
    private final CombatEntity defender;
    private final CombatBonus damageBonus;
    private final DamageType damageType;
}
