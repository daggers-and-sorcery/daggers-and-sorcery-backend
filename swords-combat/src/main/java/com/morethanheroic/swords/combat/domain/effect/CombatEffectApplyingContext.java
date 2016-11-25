package com.morethanheroic.swords.combat.domain.effect;

import java.util.List;
import java.util.Map;

import com.morethanheroic.session.domain.SessionEntity;
import com.morethanheroic.swords.combat.domain.step.CombatStep;
import com.morethanheroic.swords.effect.domain.EffectApplyingContext;
import com.morethanheroic.swords.effect.domain.EffectSettingDefinitionHolder;

import lombok.Builder;
import lombok.Getter;

/**
 * Context used when applying an effect.
 */
@Builder
public class CombatEffectApplyingContext implements EffectApplyingContext {

    @Getter
    private EffectSettingDefinitionHolder effectSettings;

    @Getter
    private CombatEffectTarget source;

    @Getter
    private CombatEffectTarget destination;

    @Getter
    private List<CombatStep> combatSteps;

    @Getter
    private SessionEntity sessionEntity;

    @Getter
    private Map<String, Object> parameters;

    public void addCombatStep(final CombatStep combatStep) {
        combatSteps.add(combatStep);
    }
}
