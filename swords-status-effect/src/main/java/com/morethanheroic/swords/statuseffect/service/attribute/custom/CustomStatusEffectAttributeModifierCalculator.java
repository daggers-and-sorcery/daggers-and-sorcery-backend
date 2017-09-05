package com.morethanheroic.swords.statuseffect.service.attribute.custom;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.statuseffect.service.attribute.custom.domain.CustomModifier;
import com.morethanheroic.swords.statuseffect.service.attribute.domain.modifier.StatusEffectAttributeModifierCalculationResult;
import com.morethanheroic.swords.user.domain.UserEntity;

import java.util.List;

public interface CustomStatusEffectAttributeModifierCalculator {

    List<StatusEffectAttributeModifierCalculationResult> calculate(UserEntity userEntity, Attribute attribute);

    CustomModifier supportedEffectId();
}
