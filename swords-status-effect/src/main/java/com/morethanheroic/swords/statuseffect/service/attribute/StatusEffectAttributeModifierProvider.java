package com.morethanheroic.swords.statuseffect.service.attribute;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.domain.type.AttributeModifierType;
import com.morethanheroic.swords.attribute.domain.type.AttributeModifierUnitType;
import com.morethanheroic.swords.attribute.service.calc.AttributeCalculationResultFactory;
import com.morethanheroic.swords.attribute.service.calc.domain.calculation.DiceValueAttributeCalculationResult;
import com.morethanheroic.swords.attribute.service.modifier.calculator.AttributeModifierProvider;
import com.morethanheroic.swords.attribute.service.modifier.domain.AttributeModifierEntry;
import com.morethanheroic.swords.attribute.service.modifier.domain.AttributeModifierValue;
import com.morethanheroic.swords.attribute.service.modifier.domain.CombatAttributeModifierValue;
import com.morethanheroic.swords.statuseffect.service.StatusEffectEntityFactory;
import com.morethanheroic.swords.statuseffect.service.attribute.domain.modifier.StatusEffectAttributeModifierCalculationResult;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatusEffectAttributeModifierProvider implements AttributeModifierProvider {

    private final StatusEffectEntityFactory statusEffectEntityFactory;
    private final StatusEffectModifierToAttributeConverter statusEffectModifierToAttributeConverter;
    private final AttributeCalculationResultFactory attributeCalculationResultFactory;
    private final StatusEffectAttributeModifierCalculator statusEffectAttributeModifierCalculator;

    @Override
    public List<AttributeModifierEntry> calculateModifiers(final UserEntity userEntity, final Attribute attribute) {
        return statusEffectEntityFactory.getEntity(userEntity).stream()
                .flatMap(statusEffectEntity -> statusEffectEntity.getStatusEffect().getModifiers().stream())
                .map(statusEffectModifierDefinition -> statusEffectAttributeModifierCalculator.calculate(userEntity, statusEffectModifierDefinition, attribute))
                .flatMap(statusEffectAttributeModifierCalculationResults -> statusEffectAttributeModifierCalculationResults.stream())
                .filter(statusEffectModifierDefinition -> isModifierForAttribute(statusEffectModifierDefinition, attribute))
                .map(statusEffectModifierDefinitionConsumer -> {
                    if (attribute instanceof CombatAttribute) {
                        final DiceValueAttributeCalculationResult combatAttributeCalculationResult = (DiceValueAttributeCalculationResult) attributeCalculationResultFactory.newResult(attribute);

                        combatAttributeCalculationResult.setValue(statusEffectModifierDefinitionConsumer.getAmount());
                        combatAttributeCalculationResult.setD2(statusEffectModifierDefinitionConsumer.getD2());
                        combatAttributeCalculationResult.setD4(statusEffectModifierDefinitionConsumer.getD4());
                        combatAttributeCalculationResult.setD6(statusEffectModifierDefinitionConsumer.getD6());
                        combatAttributeCalculationResult.setD8(statusEffectModifierDefinitionConsumer.getD8());
                        combatAttributeCalculationResult.setD10(statusEffectModifierDefinitionConsumer.getD10());

                        return new AttributeModifierEntry(AttributeModifierType.STATUS_EFFECT, AttributeModifierUnitType.VALUE,
                                new CombatAttributeModifierValue(combatAttributeCalculationResult));
                    } else {
                        return new AttributeModifierEntry(AttributeModifierType.STATUS_EFFECT, AttributeModifierUnitType.VALUE,
                                new AttributeModifierValue(statusEffectModifierDefinitionConsumer.getAmount()));
                    }
                })
                .collect(Collectors.toList());
    }

    private boolean isModifierForAttribute(final StatusEffectAttributeModifierCalculationResult statusEffectAttributeModifierCalculationResult, final Attribute attribute) {
        return statusEffectModifierToAttributeConverter.convert(statusEffectAttributeModifierCalculationResult.getModifier()) == attribute;
    }
}
