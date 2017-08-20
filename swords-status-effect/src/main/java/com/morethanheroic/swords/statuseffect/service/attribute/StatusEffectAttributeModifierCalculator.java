package com.morethanheroic.swords.statuseffect.service.attribute;

import com.morethanheroic.swords.statuseffect.service.attribute.custom.CustomStatusEffectAttributeModifierCalculatorProvider;
import com.morethanheroic.swords.statuseffect.service.attribute.domain.modifier.StatusEffectAttributeModifierCalculationResult;
import com.morethanheroic.swords.statuseffect.service.attribute.domain.modifier.impl.DefinitionBasedStatusEffectAttributeModifierCalculationResult;
import com.morethanheroic.swords.statuseffect.service.definition.domain.modifier.StatusEffectBasicModifierDefinition;
import com.morethanheroic.swords.statuseffect.service.definition.domain.modifier.StatusEffectCustomModifierDefinition;
import com.morethanheroic.swords.statuseffect.service.definition.domain.modifier.StatusEffectModifierDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatusEffectAttributeModifierCalculator {

    private CustomStatusEffectAttributeModifierCalculatorProvider customStatusEffectAttributeModifierCalculatorProvider;

    public StatusEffectAttributeModifierCalculationResult calculate(final UserEntity userEntity, final StatusEffectModifierDefinition statusEffectModifierDefinition) {
        if (statusEffectModifierDefinition instanceof StatusEffectBasicModifierDefinition) {
            return DefinitionBasedStatusEffectAttributeModifierCalculationResult.builder()
                    .statusEffectModifierDefinition((StatusEffectBasicModifierDefinition) statusEffectModifierDefinition)
                    .build();
        } else {
            final StatusEffectCustomModifierDefinition statusEffectCustomModifierDefinition = (StatusEffectCustomModifierDefinition) statusEffectModifierDefinition;

            return customStatusEffectAttributeModifierCalculatorProvider.getCalculator(statusEffectCustomModifierDefinition.getEffectId()).calculate(userEntity);
        }
    }
}
