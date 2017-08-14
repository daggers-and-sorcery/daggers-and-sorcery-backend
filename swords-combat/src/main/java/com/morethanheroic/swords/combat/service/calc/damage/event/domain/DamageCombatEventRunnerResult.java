package com.morethanheroic.swords.combat.service.calc.damage.event.domain;

import com.morethanheroic.swords.combat.step.domain.CombatStep;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class DamageCombatEventRunnerResult {

    private final List<CombatStep> combatSteps;
    private final int damage;
}
