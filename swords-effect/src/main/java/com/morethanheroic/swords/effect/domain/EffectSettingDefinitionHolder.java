package com.morethanheroic.swords.effect.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

/**
 * Holds the settings for an effect.
 */
@Builder
public class EffectSettingDefinitionHolder {

    @Getter
    private final String effectId;
    private final Map<String, EffectSettingDefinition> settings;

    public EffectSettingDefinition getSetting(String name) {
        return settings.get(name);
    }

    public int getSettingAsInt(final String settingsName) {
        return settings.get(settingsName) == null ? 0 : Integer.parseInt(settings.get(settingsName).getValue());
    }
}
