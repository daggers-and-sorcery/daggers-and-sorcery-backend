package com.morethanheroic.swords.combat.service.calc.damage.event.after.domain;

import com.morethanheroic.swords.combat.step.domain.CombatStep;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class AfterDamageEventResult {

    private final List<CombatStep> combatSteps;
}
