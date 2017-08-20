package com.morethanheroic.swords.statuseffect.service.attribute.domain.modifier.impl;

import com.morethanheroic.swords.statuseffect.service.attribute.domain.modifier.StatusEffectAttributeModifierCalculationResult;
import com.morethanheroic.swords.statuseffect.service.definition.domain.StatusEffectModifier;
import com.morethanheroic.swords.statuseffect.service.definition.domain.modifier.StatusEffectBasicModifierDefinition;
import lombok.Builder;

@Builder
public class DefinitionBasedStatusEffectAttributeModifierCalculationResult implements StatusEffectAttributeModifierCalculationResult {

    private final StatusEffectBasicModifierDefinition statusEffectModifierDefinition;

    @Override
    public StatusEffectModifier getModifier() {
        return statusEffectModifierDefinition.getModifier();
    }

    @Override
    public int getAmount() {
        return statusEffectModifierDefinition.getAmount();
    }

    @Override
    public int getD2() {
        return statusEffectModifierDefinition.getD2();
    }

    @Override
    public int getD4() {
        return statusEffectModifierDefinition.getD4();
    }

    @Override
    public int getD6() {
        return statusEffectModifierDefinition.getD6();
    }

    @Override
    public int getD8() {
        return statusEffectModifierDefinition.getD8();
    }

    @Override
    public int getD10() {
        return statusEffectModifierDefinition.getD10();
    }
}
