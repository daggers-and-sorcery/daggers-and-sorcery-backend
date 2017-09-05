package com.morethanheroic.swords.combat.service.event.damage.after.domain;

import java.util.List;

import com.morethanheroic.swords.combat.step.domain.CombatStep;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AfterDamageCombatEventResult {

    private final List<CombatStep> combatSteps;
}
