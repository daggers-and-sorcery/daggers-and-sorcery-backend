package com.morethanheroic.swords.combat.domain.effect;

import com.morethanheroic.swords.combat.domain.CombatEffectDataHolder;
import com.morethanheroic.swords.effect.domain.EffectDefinition;

public abstract class CombatEffectDefinition implements EffectDefinition {

    public abstract void apply(CombatEffectApplyingContext effectApplyingContext, CombatEffectDataHolder combatEffectDataHolder);

    public abstract String getId();
}
