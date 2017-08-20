package com.morethanheroic.swords.statuseffect.service.attribute;

import com.google.common.collect.Lists;
import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.statuseffect.service.attribute.custom.CustomStatusEffectAttributeModifierCalculatorProvider;
import com.morethanheroic.swords.statuseffect.service.attribute.domain.modifier.StatusEffectAttributeModifierCalculationResult;
import com.morethanheroic.swords.statuseffect.service.attribute.domain.modifier.impl.DefinitionBasedStatusEffectAttributeModifierCalculationResult;
import com.morethanheroic.swords.statuseffect.service.definition.domain.modifier.StatusEffectBasicModifierDefinition;
import com.morethanheroic.swords.statuseffect.service.definition.domain.modifier.StatusEffectCustomModifierDefinition;
import com.morethanheroic.swords.statuseffect.service.definition.domain.modifier.StatusEffectModifierDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatusEffectAttributeModifierCalculator {

    private final CustomStatusEffectAttributeModifierCalculatorProvider customStatusEffectAttributeModifierCalculatorProvider;

    public List<StatusEffectAttributeModifierCalculationResult> calculate(final UserEntity userEntity, final StatusEffectModifierDefinition statusEffectModifierDefinition, Attribute attribute) {
        if (statusEffectModifierDefinition instanceof StatusEffectBasicModifierDefinition) {
            return Lists.newArrayList(
                    DefinitionBasedStatusEffectAttributeModifierCalculationResult.builder()
                            .statusEffectModifierDefinition((StatusEffectBasicModifierDefinition) statusEffectModifierDefinition)
                            .build()
            );
        } else {
            final StatusEffectCustomModifierDefinition statusEffectCustomModifierDefinition = (StatusEffectCustomModifierDefinition) statusEffectModifierDefinition;

            return customStatusEffectAttributeModifierCalculatorProvider.getCalculator(statusEffectCustomModifierDefinition.getEffectId()).calculate(userEntity, attribute);
        }
    }
}
