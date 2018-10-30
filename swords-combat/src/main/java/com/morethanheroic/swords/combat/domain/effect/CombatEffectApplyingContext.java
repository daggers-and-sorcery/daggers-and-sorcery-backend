package com.morethanheroic.swords.combat.domain.effect;

import java.util.List;
import java.util.Map;

import com.morethanheroic.session.domain.SessionEntity;
import com.morethanheroic.swords.combat.step.domain.CombatStep;
import com.morethanheroic.swords.effect.domain.EffectApplyingContext;
import com.morethanheroic.swords.effect.domain.EffectSettingDefinitionHolder;

import lombok.Builder;
import lombok.Getter;

/**
 * Context used when applying an effect.
 */
@Getter
@Builder
public class CombatEffectApplyingContext implements EffectApplyingContext {

    private final EffectSettingDefinitionHolder effectSettings;
    private final CombatEffectTarget source;
    private final CombatEffectTarget destination;
    private final List<CombatStep> combatSteps;
    private final SessionEntity sessionEntity;
    private final Map<String, Object> parameters;

    public void addCombatStep(final CombatStep combatStep) {
        combatSteps.add(combatStep);
    }
}
