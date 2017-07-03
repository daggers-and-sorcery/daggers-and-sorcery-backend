package com.morethanheroic.swords.combat.service.event.damage;

import com.morethanheroic.swords.combat.domain.step.CombatStep;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * Contains the result of a damage event calculation.
 */
@Getter
@Builder
public class DamageEventCalculationResult {

    private final List<CombatStep> combatSteps;
    private final int bonusDamage;
}
