package com.morethanheroic.swords.effect.domain;

/**
 * One variable for a spell effect.
 */
public class EffectSetting {

    private final String name;
    private final String value;

    public EffectSetting(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
