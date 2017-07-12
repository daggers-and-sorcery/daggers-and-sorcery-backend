package com.morethanheroic.swords.combat.domain;

import com.morethanheroic.swords.combat.step.domain.CombatStep;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class AttackResult {

    private final List<CombatStep> attackResult;
    private final boolean combatEnded;
    private final Winner winner;
}
