package com.morethanheroic.swords.statuseffect.service.definition.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * Contains the definitions of an status effect modifier.
 */
@Builder
@Getter
@ToString
public class StatusEffectModifierDefinition {

    private final StatusEffectModifier modifier;
    private final int amount;
    private final int d2;
    private final int d4;
    private final int d6;
    private final int d8;
    private final int d10;
}