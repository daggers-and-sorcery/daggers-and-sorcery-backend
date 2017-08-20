package com.morethanheroic.swords.statuseffect.service.attribute.domain.modifier;

import com.morethanheroic.swords.statuseffect.service.definition.domain.StatusEffectModifier;

public interface StatusEffectAttributeModifierCalculationResult {

    StatusEffectModifier getModifier();

    int getAmount();

    int getD2();

    int getD4();

    int getD6();

    int getD8();

    int getD10();
}
