package com.morethanheroic.swords.statuseffect.service.attribute.domain.modifier.impl;

import com.morethanheroic.swords.statuseffect.service.attribute.domain.modifier.StatusEffectAttributeModifierCalculationResult;
import com.morethanheroic.swords.statuseffect.service.definition.domain.StatusEffectModifier;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CustomStatusEffectAttributeModifierCalculationResult implements StatusEffectAttributeModifierCalculationResult {

    private final StatusEffectModifier modifier;
    private final int amount;
    private final int d2;
    private final int d4;
    private final int d6;
    private final int d8;
    private final int d10;
}
