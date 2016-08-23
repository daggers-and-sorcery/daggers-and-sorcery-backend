package com.morethanheroic.swords.combat.service.flee.domain;

import com.morethanheroic.swords.combat.domain.step.CombatStep;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class FleeResult {

    private final List<CombatStep> combatSteps;
    private final boolean successful;
}
