package com.morethanheroic.swords.effect.service.domain;

import lombok.ToString;

import java.util.List;

/**
 * A base class for freshly loaded effects.
 */
@ToString
public abstract class RawEffectDefinition {

    public abstract String getTarget();

    public abstract List<? extends RawEffectSetting> getEffectSettings();
}
