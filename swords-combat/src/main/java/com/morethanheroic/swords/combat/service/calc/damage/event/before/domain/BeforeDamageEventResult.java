package com.morethanheroic.swords.combat.service.calc.damage.event.before.domain;

import com.morethanheroic.swords.combat.bonus.domain.CombatBonus;
import com.morethanheroic.swords.combat.step.domain.CombatStep;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class BeforeDamageEventResult {

    private final List<CombatStep> combatSteps;
    private final CombatBonus bonusDamage;
}
