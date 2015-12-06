package com.morethanheroic.swords.effect.domain;

import java.util.HashMap;

public class EffectSettingHolder {

    private final HashMap<String, EffectSetting> settings;

    public EffectSettingHolder(HashMap<String, EffectSetting> settings) {
        this.settings = settings;
    }

    public EffectSetting getSetting(String name) {
        return settings.get(name);
    }
}
