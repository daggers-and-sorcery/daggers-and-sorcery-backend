package com.morethanheroic.swords.combat.service.event.damage.domain;

import com.morethanheroic.swords.combat.step.domain.CombatStep;
import lombok.Builder;
import lombok.Getter;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Builder
public class DamageCombatEventHandlerResult {

    private final List<CombatStep> combatSteps;

    @Getter
    private final int addedDamage;

    public List<CombatStep> getCombatSteps() {
        return combatSteps == null ? Collections.emptyList() : combatSteps;
    }
}
