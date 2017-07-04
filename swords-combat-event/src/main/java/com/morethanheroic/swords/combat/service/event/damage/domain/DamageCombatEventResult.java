package com.morethanheroic.swords.combat.service.event.damage.domain;

import com.morethanheroic.swords.combat.bonus.domain.CombatBonus;
import com.morethanheroic.swords.combat.step.domain.CombatStep;
import lombok.Builder;
import lombok.Getter;

import java.util.Collections;
import java.util.List;

/**
 * Contains the result of a damage event calculation.
 */
@Getter
@Builder
public class DamageCombatEventResult {

    public static final DamageCombatEventResult EMPTY_RESULT = DamageCombatEventResult.builder()
            .combatSteps(Collections.emptyList())
            .bonusDamage(CombatBonus.EMPTY_COMBAT_BONUS)
            .build();

    private final List<CombatStep> combatSteps;
    private final CombatBonus bonusDamage;
}
