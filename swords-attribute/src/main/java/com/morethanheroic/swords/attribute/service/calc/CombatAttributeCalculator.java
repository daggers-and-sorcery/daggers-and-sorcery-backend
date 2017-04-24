package com.morethanheroic.swords.attribute.service.calc;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.domain.GeneralAttribute;
import com.morethanheroic.swords.attribute.service.bonus.AttributeBonusProvider;
import com.morethanheroic.swords.attribute.service.calc.domain.calculation.AttributeCalculationResult;
import com.morethanheroic.swords.attribute.service.calc.domain.calculation.CombatAttributeCalculationResult;
import com.morethanheroic.swords.attribute.service.calc.domain.data.AttributeData;
import com.morethanheroic.swords.attribute.service.modifier.calculator.GlobalAttributeModifierCalculator;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Used to calculate a {@link com.morethanheroic.swords.attribute.domain.type.AttributeType#COMBAT} attribute's all data related to the player.
 */
@Service
@RequiredArgsConstructor
public class CombatAttributeCalculator extends GenericAttributeCalculator<CombatAttribute> {

    private final GlobalAttributeCalculator globalAttributeCalculator;
    private final GlobalAttributeModifierCalculator globalAttributeModifierCalculator;
    private final List<AttributeBonusProvider> attributeBonusProviders;

    @Override
    public AttributeData calculateAttributeValue(UserEntity user, CombatAttribute attribute) {
        return AttributeData.attributeDataBuilder()
                .attribute(attribute)
                .actual(globalAttributeCalculator.calculateActualValue(user, attribute))
                .maximum(globalAttributeCalculator.calculateMaximumValue(user, attribute))
                .modifierData(globalAttributeModifierCalculator.calculateModifierData(user, attribute))
                .build();
    }

    @Override
    public Class<CombatAttribute> getSupportedType() {
        return CombatAttribute.class;
    }

    @Override
    public AttributeCalculationResult calculateActualValue(UserEntity user, Attribute attribute, boolean shouldCheckMinimum) {
        if (attribute == CombatAttribute.LIFE) {
            return new CombatAttributeCalculationResult(user.getHealthPoints(), (CombatAttribute) attribute);
        } else if (attribute == CombatAttribute.MANA) {
            return new CombatAttributeCalculationResult(user.getManaPoints(), (CombatAttribute) attribute);
        }

        return super.calculateActualValue(user, attribute, shouldCheckMinimum);
    }

    @Override
    public AttributeCalculationResult calculateActualBeforePercentageMultiplication(final UserEntity userEntity, final Attribute attribute) {
        final AttributeCalculationResult result = new CombatAttributeCalculationResult((CombatAttribute) attribute);

        attributeBonusProviders.forEach(attributeBonusProvider -> attributeBonusProvider.calculateBonus(userEntity, attribute)
                .ifPresent(result::addCalculationResult)
        );

        result.increaseValue(attribute.getInitialValue());
        result.increaseValue(calculateAllBonusByGeneralAttributes(userEntity, (CombatAttribute) attribute));

        return result;
    }

    @Override
    public AttributeCalculationResult calculateMaximumBeforePercentageMultiplication(UserEntity userEntity, Attribute attribute) {
        final AttributeCalculationResult result = super.calculateMaximumBeforePercentageMultiplication(userEntity, attribute);

        result.increaseValue(calculateAllBonusByGeneralAttributes(userEntity, (CombatAttribute) attribute));

        return result;
    }

    public int calculateAllBonusByGeneralAttributes(UserEntity user, CombatAttribute attribute) {
        int result = 0;

        for (GeneralAttribute target : attribute.getBonusAttributes()) {
            result += calculateBonusByGeneralAttribute(user, target, attribute.getBonusPercentage());
        }

        return result;
    }

    public int calculateBonusByGeneralAttribute(UserEntity user, GeneralAttribute attribute, double bonusPercentage) {
        return (int) Math.floor(globalAttributeCalculator.calculateActualValue(user, attribute).getValue() * bonusPercentage);
    }

    public int calculateMinimumAttributeBonuses(UserEntity userEntity, CombatAttribute attribute) {
        if (attribute.getMinimalValue() == 0) {
            return 0;
        }

        final AttributeCalculationResult originalAttributeValue = globalAttributeCalculator.calculateActualValue(userEntity, attribute, false);

        if (originalAttributeValue.getValue() < attribute.getMinimalValue()) {
            return attribute.getMinimalValue() - originalAttributeValue.getValue();
        }

        return 0;
    }
}
