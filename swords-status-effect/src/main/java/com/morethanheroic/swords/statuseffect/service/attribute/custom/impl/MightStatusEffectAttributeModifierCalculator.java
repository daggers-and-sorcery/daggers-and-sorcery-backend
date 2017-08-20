package com.morethanheroic.swords.statuseffect.service.attribute.custom.impl;

import com.morethanheroic.swords.statuseffect.service.attribute.custom.CustomStatusEffectAttributeModifierCalculator;
import com.morethanheroic.swords.statuseffect.service.attribute.custom.domain.CustomModifier;
import com.morethanheroic.swords.statuseffect.service.attribute.domain.modifier.StatusEffectAttributeModifierCalculationResult;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class MightStatusEffectAttributeModifierCalculator implements CustomStatusEffectAttributeModifierCalculator {

    @Override
    public StatusEffectAttributeModifierCalculationResult calculate(final UserEntity userEntity) {
        return null;
    }

    @Override
    public CustomModifier supportedEffectId() {
        return CustomModifier.MIGHT;
    }
}
