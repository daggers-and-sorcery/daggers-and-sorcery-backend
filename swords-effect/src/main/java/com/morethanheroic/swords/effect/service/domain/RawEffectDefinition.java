package com.morethanheroic.swords.effect.service.domain;

import java.util.ArrayList;

/**
 * A base class for freshly loaded effects.
 */
public abstract class RawEffectDefinition {

    public abstract String getTarget();

    public abstract ArrayList<? extends RawEffectSetting> getEffectSettings();
}
