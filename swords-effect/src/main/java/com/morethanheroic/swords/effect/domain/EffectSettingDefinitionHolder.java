package com.morethanheroic.swords.effect.domain;

import java.util.Map;

/**
 * Holds the settings for an effect.
 */
public class EffectSettingDefinitionHolder {

    private final Map<String, EffectSettingDefinition> settings;

    public EffectSettingDefinitionHolder(Map<String, EffectSettingDefinition> settings) {
        this.settings = settings;
    }

    public EffectSettingDefinition getSetting(String name) {
        return settings.get(name);
    }
}
