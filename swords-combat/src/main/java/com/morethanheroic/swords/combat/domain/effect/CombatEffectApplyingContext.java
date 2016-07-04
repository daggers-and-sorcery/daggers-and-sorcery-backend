package com.morethanheroic.swords.combat.domain.effect;

import com.morethanheroic.swords.combat.domain.CombatResult;
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
    private CombatResult combatResult;
}
