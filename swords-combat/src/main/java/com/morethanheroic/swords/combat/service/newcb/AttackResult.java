package com.morethanheroic.swords.combat.service.newcb;

import com.morethanheroic.swords.combat.domain.step.CombatStep;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class AttackResult {

    private final List<CombatStep> attackResult;
    private final boolean combatEnded;
}
