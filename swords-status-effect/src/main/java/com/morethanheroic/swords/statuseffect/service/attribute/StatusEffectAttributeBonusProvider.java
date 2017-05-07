package com.morethanheroic.swords.statuseffect.service.attribute;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.service.bonus.AttributeBonusProvider;
import com.morethanheroic.swords.attribute.service.calc.domain.calculation.AttributeCalculationResult;
import com.morethanheroic.swords.attribute.service.calc.domain.calculation.CombatAttributeCalculationResult;
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
        final AttributeCalculationResult attributeCalculationResult = buildResult(attribute);

        statusEffectEntityFactory.getEntity(userEntity).stream()
            .flatMap(statusEffectEntity -> statusEffectEntity.getStatusEffect().getModifiers().stream())
            .filter(statusEffectModifierDefinition -> isModifierForAttribute(statusEffectModifierDefinition, attribute))
            .forEach(statusEffectModifierDefinitionConsumer -> {
                attributeCalculationResult.increaseValue(statusEffectModifierDefinitionConsumer.getAmount());

                if (attribute instanceof CombatAttribute) {
                    final CombatAttributeCalculationResult combatAttributeCalculationResult = (CombatAttributeCalculationResult) attributeCalculationResult;

                    combatAttributeCalculationResult.increaseD2(statusEffectModifierDefinitionConsumer.getD2());
                    combatAttributeCalculationResult.increaseD4(statusEffectModifierDefinitionConsumer.getD4());
                    combatAttributeCalculationResult.increaseD6(statusEffectModifierDefinitionConsumer.getD6());
                    combatAttributeCalculationResult.increaseD8(statusEffectModifierDefinitionConsumer.getD8());
                    combatAttributeCalculationResult.increaseD10(statusEffectModifierDefinitionConsumer.getD10());
                }
            });

        return Optional.of(attributeCalculationResult);
    }

    private AttributeCalculationResult buildResult(final Attribute attribute) {
        return attribute instanceof CombatAttribute ? new CombatAttributeCalculationResult((CombatAttribute) attribute)
                : new AttributeCalculationResult(attribute);
    }

    private boolean isModifierForAttribute(final StatusEffectModifierDefinition statusEffectModifierDefinition, final Attribute attribute) {
        return statusEffectModifierToAttributeConverter.convert(statusEffectModifierDefinition.getModifier()) == attribute;
    }
}
