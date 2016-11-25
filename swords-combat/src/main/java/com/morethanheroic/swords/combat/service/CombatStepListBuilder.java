package com.morethanheroic.swords.combat.service;

import com.morethanheroic.swords.combat.domain.step.CombatStep;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class CombatStepListBuilder {

    private List<CombatStep> combatSteps;

    public static CombatStepListBuilder builder() {
        return new CombatStepListBuilder();
    }

    public CombatStepListBuilder withCombatStep(final CombatStep combatStep) {
        combatSteps.add(combatStep);

        return this;
    }

    public CombatStepListBuilder withCombatSteps(final Collection<CombatStep> combatSteps) {
        this.combatSteps.addAll(combatSteps);

        return this;
    }

    public List<CombatStep> build() {
        return combatSteps;
    }
}
