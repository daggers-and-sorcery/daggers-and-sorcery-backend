package com.morethanheroic.swords.combat.domain.effect;

import com.morethanheroic.swords.combat.domain.CombatEffectDataHolder;

public abstract class ImprovedCombatEffectDefinition extends CombatEffectDefinition {

    public abstract void apply(final CombatEffectApplyingContext effectApplyingContext);

    /**
     * @deprecated Do not use! Use {@link #apply(CombatEffectApplyingContext)} instead.
     */
    @Override
    @Deprecated
    public void apply(CombatEffectApplyingContext effectApplyingContext, CombatEffectDataHolder combatEffectDataHolder) {
        apply(effectApplyingContext);
    }
}
