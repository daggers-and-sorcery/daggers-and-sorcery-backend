package com.morethanheroic.swords.statuseffect.service.attribute.custom;

import com.morethanheroic.swords.statuseffect.service.attribute.custom.domain.CustomModifier;
import com.morethanheroic.swords.statuseffect.service.attribute.domain.modifier.StatusEffectAttributeModifierCalculationResult;
import com.morethanheroic.swords.user.domain.UserEntity;

public interface CustomStatusEffectAttributeModifierCalculator {

    StatusEffectAttributeModifierCalculationResult calculate(UserEntity userEntity);

    CustomModifier supportedEffectId();
}
