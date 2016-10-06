package com.morethanheroic.swords.effect.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * One variable for a spell effect.
 */
@Getter
@RequiredArgsConstructor
public class EffectSettingDefinition {

    private final String name;
    private final String value;
}
