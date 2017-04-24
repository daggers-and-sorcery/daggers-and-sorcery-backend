package com.morethanheroic.swords.attribute.service.calc;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.service.bonus.AttributeBonusProvider;
import com.morethanheroic.swords.attribute.service.calc.domain.calculation.AttributeCalculationResult;
import com.morethanheroic.swords.attribute.service.calc.domain.calculation.UnlimitedAttributeCalculationResult;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class GenericAttributeCalculator<T extends Attribute> implements AttributeCalculator<T> {

    @Autowired
    private List<AttributeBonusProvider> attributeBonusProviders;

    @Override
    public AttributeCalculationResult calculateActualValue(UserEntity user, Attribute attribute, boolean shouldCheckMinimum) {
        final int racialModifier = calculatePercentageModification(user, attribute);

        final AttributeCalculationResult percentageResult = calculatePercentageModifiedAttribute(calculateActualBeforePercentageMultiplication(user, attribute), racialModifier);

        if (shouldCheckMinimum) {
            checkMinimumValue(attribute, percentageResult);
        }

        return percentageResult;
    }


    protected int calculatePercentageModification(UserEntity userEntity, Attribute attribute) {
        return 0;
    }

    public AttributeCalculationResult calculateActualBeforePercentageMultiplication(UserEntity user, Attribute attribute) {
        final AttributeCalculationResult result = new AttributeCalculationResult(attribute);

        for (AttributeBonusProvider attributeBonusProvider : attributeBonusProviders) {
            attributeBonusProvider.calculateBonus(user, attribute).ifPresent(result::addCalculationResult);
        }

        return result;
    }

    public AttributeCalculationResult calculatePercentageModifiedAttribute(AttributeCalculationResult attributeValue, int percentage) {
        attributeValue.setValue((int) (attributeValue.getValue() * ((double) percentage / 100 + 1)));

        return attributeValue;
    }

    public AttributeCalculationResult calculateMaximumValue(UserEntity user, Attribute attribute) {
        if (attribute.isUnlimited()) {
            return new UnlimitedAttributeCalculationResult(attribute);
        }

        final int racialModifier = calculatePercentageModification(user, attribute);

        return calculatePercentageModifiedAttribute(calculateMaximumBeforePercentageMultiplication(user, attribute), racialModifier);
    }

    public AttributeCalculationResult calculateMaximumBeforePercentageMultiplication(UserEntity userEntity, Attribute attribute) {
        final AttributeCalculationResult result = new AttributeCalculationResult(attribute);

        result.increaseValue(attribute.getInitialValue());

        for (AttributeBonusProvider attributeBonusProvider : attributeBonusProviders) {
            attributeBonusProvider.calculateBonus(userEntity, attribute).ifPresent(result::addCalculationResult);
        }

        return result;
    }

    protected void checkMinimumValue(final Attribute attribute, final AttributeCalculationResult attributeCalculationResult) {
    }
}
