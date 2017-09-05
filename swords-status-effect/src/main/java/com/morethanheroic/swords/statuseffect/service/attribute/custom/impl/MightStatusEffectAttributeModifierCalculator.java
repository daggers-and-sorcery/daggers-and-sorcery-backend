package com.morethanheroic.swords.statuseffect.service.attribute.custom.impl;

import com.google.common.collect.Lists;
import com.morethanheroic.math.PercentageCalculator;
import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.domain.GeneralAttribute;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.statuseffect.service.attribute.custom.CustomStatusEffectAttributeModifierCalculator;
import com.morethanheroic.swords.statuseffect.service.attribute.custom.domain.CustomModifier;
import com.morethanheroic.swords.statuseffect.service.attribute.domain.modifier.StatusEffectAttributeModifierCalculationResult;
import com.morethanheroic.swords.statuseffect.service.attribute.domain.modifier.impl.CustomStatusEffectAttributeModifierCalculationResult;
import com.morethanheroic.swords.statuseffect.service.definition.domain.StatusEffectModifier;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MightStatusEffectAttributeModifierCalculator implements CustomStatusEffectAttributeModifierCalculator {

    private static final int ATTACK_BONUS_PERCENTAGE = 10;
    private static final int DAMAGE_BONUS_PERCENTAGE = 10;
    private static final int DEFENSE_BONUS_PERCENTAGE = 15;

    private final PercentageCalculator percentageCalculator;
    private final GlobalAttributeCalculator globalAttributeCalculator;

    @Override
    public List<StatusEffectAttributeModifierCalculationResult> calculate(final UserEntity userEntity, final Attribute attribute) {
        if (attribute == GeneralAttribute.WILLPOWER) {
            return Collections.emptyList();
        }

        final int willpowerAmount = globalAttributeCalculator.calculateActualValue(userEntity, GeneralAttribute.WILLPOWER).getValue();

        return Lists.newArrayList(
                CustomStatusEffectAttributeModifierCalculationResult.builder()
                        .modifier(StatusEffectModifier.ATTACK)
                        .amount((int) Math.floor(percentageCalculator.claclulatePercentageOf(ATTACK_BONUS_PERCENTAGE, willpowerAmount)))
                        .build(),
                CustomStatusEffectAttributeModifierCalculationResult.builder()
                        .modifier(StatusEffectModifier.DAMAGE)
                        .amount((int) Math.floor(percentageCalculator.claclulatePercentageOf(DAMAGE_BONUS_PERCENTAGE, willpowerAmount)))
                        .build(),
                CustomStatusEffectAttributeModifierCalculationResult.builder()
                        .modifier(StatusEffectModifier.DEFENSE)
                        .amount((int) Math.floor(percentageCalculator.claclulatePercentageOf(DEFENSE_BONUS_PERCENTAGE, willpowerAmount)))
                        .build()
        );
    }

    @Override
    public CustomModifier supportedEffectId() {
        return CustomModifier.MIGHT;
    }
}
