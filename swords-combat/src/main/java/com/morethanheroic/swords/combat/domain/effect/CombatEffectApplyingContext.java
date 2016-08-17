package com.morethanheroic.swords.combat.domain.effect;

import java.util.List;

import com.morethanheroic.swords.combat.domain.step.CombatStep;
import com.morethanheroic.swords.effect.domain.EffectApplyingContext;
import com.morethanheroic.swords.effect.domain.EffectSettingDefinitionHolder;

import lombok.Builder;
import lombok.Getter;

/**
 * Context used when applying an effect.
 */
@Builder
@Getter
public class CombatEffectApplyingContext implements EffectApplyingContext {

    private EffectSettingDefinitionHolder effectSettings;
    private CombatEffectTarget source;
    private CombatEffectTarget destination;
    private List<CombatStep> combatSteps;

    public void addCombatStep(final CombatStep combatStep) {
        combatSteps.add(combatStep);
    }
}
