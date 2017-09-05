package com.morethanheroic.swords.statuseffect.service.attribute;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.service.bonus.AttributeBonusProvider;
import com.morethanheroic.swords.attribute.service.calc.AttributeCalculationResultFactory;
import com.morethanheroic.swords.attribute.service.calc.domain.calculation.DiceValueAttributeCalculationResult;
import com.morethanheroic.swords.attribute.service.calc.domain.calculation.SimpleValueAttributeCalculationResult;
import com.morethanheroic.swords.statuseffect.service.StatusEffectEntityFactory;
import com.morethanheroic.swords.statuseffect.service.attribute.domain.modifier.StatusEffectAttributeModifierCalculationResult;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Calculate the provided bonuses of status effects.
 */
@Service
@RequiredArgsConstructor
public class StatusEffectAttributeBonusProvider implements AttributeBonusProvider {

    private final StatusEffectEntityFactory statusEffectEntityFactory;
    private final StatusEffectModifierToAttributeConverter statusEffectModifierToAttributeConverter;
    private final AttributeCalculationResultFactory attributeCalculationResultFactory;
    private final StatusEffectAttributeModifierCalculator statusEffectAttributeModifierCalculator;

    @Override
    public Optional<SimpleValueAttributeCalculationResult> calculateBonus(final UserEntity userEntity, final Attribute attribute) {
        final SimpleValueAttributeCalculationResult attributeCalculationResult = attributeCalculationResultFactory.newResult(attribute);

        statusEffectEntityFactory.getEntity(userEntity).stream()
                .flatMap(statusEffectEntity -> statusEffectEntity.getStatusEffect().getModifiers().stream())
                .map(statusEffectModifierDefinition -> statusEffectAttributeModifierCalculator.calculate(userEntity, statusEffectModifierDefinition, attribute))
                .flatMap(statusEffectAttributeModifierCalculationResults -> statusEffectAttributeModifierCalculationResults.stream())
                .filter(statusEffectModifierDefinition -> isModifierForAttribute(statusEffectModifierDefinition, attribute))
                .forEach(statusEffectModifierDefinitionConsumer -> {
                    attributeCalculationResult.increaseValue(statusEffectModifierDefinitionConsumer.getAmount());

                    if (attribute instanceof CombatAttribute) {
                        final DiceValueAttributeCalculationResult combatAttributeCalculationResult = (DiceValueAttributeCalculationResult) attributeCalculationResult;

                        combatAttributeCalculationResult.increaseD2(statusEffectModifierDefinitionConsumer.getD2());
                        combatAttributeCalculationResult.increaseD4(statusEffectModifierDefinitionConsumer.getD4());
                        combatAttributeCalculationResult.increaseD6(statusEffectModifierDefinitionConsumer.getD6());
                        combatAttributeCalculationResult.increaseD8(statusEffectModifierDefinitionConsumer.getD8());
                        combatAttributeCalculationResult.increaseD10(statusEffectModifierDefinitionConsumer.getD10());
                    }
                });

        return Optional.of(attributeCalculationResult);
    }

    private boolean isModifierForAttribute(final StatusEffectAttributeModifierCalculationResult statusEffectAttributeModifierCalculationResult, final Attribute attribute) {
        return statusEffectModifierToAttributeConverter.convert(statusEffectAttributeModifierCalculationResult.getModifier()) == attribute;
    }
}
