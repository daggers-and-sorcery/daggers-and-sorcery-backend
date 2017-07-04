package com.morethanheroic.swords.combat.service.calc.damage.event.domain;

import com.morethanheroic.swords.combat.bonus.domain.CombatBonus;
import com.morethanheroic.swords.combat.domain.step.CombatStep;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class DamageEventResult {

    private final List<CombatStep> combatSteps;
    private final CombatBonus bonusDamage;
}
