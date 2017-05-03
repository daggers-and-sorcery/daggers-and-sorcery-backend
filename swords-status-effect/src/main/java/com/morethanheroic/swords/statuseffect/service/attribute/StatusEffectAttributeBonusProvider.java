package com.morethanheroic.swords.statuseffect.service.attribute;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.service.bonus.AttributeBonusProvider;
import com.morethanheroic.swords.attribute.service.calc.domain.calculation.AttributeCalculationResult;
import com.morethanheroic.swords.attribute.service.calc.domain.calculation.CombatAttributeCalculationResult;
import com.morethanheroic.swords.statuseffect.domain.StatusEffectEntity;
import com.morethanheroic.swords.statuseffect.service.StatusEffectEntityFactory;
import com.morethanheroic.swords.statuseffect.service.definition.domain.StatusEffectModifierDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;

import lombok.RequiredArgsConstructor;

/**
 * Calculate the provided bonuses of status effects.
 */
@Service
@RequiredArgsConstructor
public class StatusEffectAttributeBonusProvider implements AttributeBonusProvider {

    private final StatusEffectEntityFactory statusEffectEntityFactory;
    private final StatusEffectModifierToAttributeConverter statusEffectModifierToAttributeConverter;

    @Override
    public Optional<AttributeCalculationResult> calculateBonus(final UserEntity userEntity, final Attribute attribute) {
        final List<StatusEffectEntity> statusEffectEntities = statusEffectEntityFactory.getEntity(userEntity);

        int attributeBonus = 0;
        int d2 = 0;
        int d4 = 0;
        int d6 = 0;
        int d8 = 0;
        int d10 = 0;
        for (StatusEffectEntity statusEffectEntity : statusEffectEntities) {
            for (StatusEffectModifierDefinition statusEffectModifierDefinition : statusEffectEntity.getStatusEffect().getModifiers()) {
                if (statusEffectModifierToAttributeConverter.convert(statusEffectModifierDefinition.getModifier()) == attribute) {
                    attributeBonus += statusEffectModifierDefinition.getAmount();
                    d2 += statusEffectModifierDefinition.getD2();
                    d4 += statusEffectModifierDefinition.getD4();
                    d6 += statusEffectModifierDefinition.getD6();
                    d8 += statusEffectModifierDefinition.getD8();
                    d10 += statusEffectModifierDefinition.getD10();
                }
            }
        }

        if (attribute instanceof CombatAttribute) {
            CombatAttributeCalculationResult result = new CombatAttributeCalculationResult(attributeBonus, (CombatAttribute) attribute);

            result.increaseD2(d2);
            result.increaseD4(d4);
            result.increaseD6(d6);
            result.increaseD8(d8);
            result.increaseD10(d10);

            return Optional.of(result);
        } else {
            return Optional.of(new AttributeCalculationResult(attributeBonus, attribute));
        }
    }
}
