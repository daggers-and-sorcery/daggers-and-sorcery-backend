package com.morethanheroic.swords.effect.service.domain;

import lombok.ToString;

import java.util.ArrayList;

/**
 * A base class for freshly loaded effects.
 */
@ToString
public abstract class RawEffectDefinition {

    public abstract String getTarget();

    public abstract ArrayList<? extends RawEffectSetting> getEffectSettings();
}
