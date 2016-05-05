package com.morethanheroic.swords.effect.service.domain;

import com.morethanheroic.swords.effect.domain.EffectSettingDefinition;
import lombok.ToString;

/**
 * A freshly loaded {@link EffectSettingDefinition}.
 */
@ToString
public abstract class RawEffectSetting {

    public abstract String getName();

    public abstract String getValue();
}
