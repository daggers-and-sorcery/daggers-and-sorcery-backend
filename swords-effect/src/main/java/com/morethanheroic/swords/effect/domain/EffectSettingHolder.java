package com.morethanheroic.swords.effect.domain;

import java.util.Map;

/**
 * Holds the settings for an effect.
 */
public class EffectSettingHolder {

    private final Map<String, EffectSetting> settings;

    public EffectSettingHolder(Map<String, EffectSetting> settings) {
        this.settings = settings;
    }

    public EffectSetting getSetting(String name) {
        return settings.get(name);
    }
}
