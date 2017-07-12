package com.morethanheroic.swords.combat.service;

import com.morethanheroic.swords.combat.step.domain.CombatStep;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CombatStepListBuilder {

    private List<CombatStep> combatSteps = new ArrayList<>();

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
