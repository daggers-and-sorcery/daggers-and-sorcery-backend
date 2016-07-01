package com.morethanheroic.swords.effect.domain;

import com.morethanheroic.swords.effect.domain.target.EffectTarget;

/**
 *
 *
 * @author Gyula_Lakatos
 */
public interface EffectApplyingContext {

    EffectSettingDefinitionHolder getEffectSettings();
    EffectTarget getSource();
    EffectTarget getDestination();
}
