package com.morethanheroic.swords.effect.service.domain;

import com.morethanheroic.swords.effect.domain.EffectSettingDefinition;

/**
 * A freshly loaded {@link EffectSettingDefinition}.
 */
public abstract class RawEffectSetting {

    public abstract String getName();

    public abstract String getValue();
}
