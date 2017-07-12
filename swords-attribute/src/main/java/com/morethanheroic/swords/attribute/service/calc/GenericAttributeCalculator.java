package com.morethanheroic.swords.attribute.service.calc;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.service.bonus.AttributeBonusProvider;
import com.morethanheroic.swords.attribute.service.calc.domain.calculation.SimpleValueAttributeCalculationResult;
import com.morethanheroic.swords.attribute.service.calc.domain.calculation.UnlimitedValueAttributeCalculationResult;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class GenericAttributeCalculator<T extends Attribute> implements AttributeCalculator<T> {

    @Autowired
    private List<AttributeBonusProvider> attributeBonusProviders;

    @Autowired
    private AttributeCalculationResultFactory attributeCalculationResultFactory;

    @Override
    public SimpleValueAttributeCalculationResult calculateActualValue(UserEntity user, Attribute attribute, boolean shouldCheckMinimum) {
        final int racialModifier = calculatePercentageModification(user, attribute);

        final SimpleValueAttributeCalculationResult percentageResult = calculatePercentageModifiedAttribute(calculateActualBeforePercentageMultiplication(user, attribute), racialModifier);

        if (shouldCheckMinimum) {
            checkMinimumValue(attribute, percentageResult);
        }

        return percentageResult;
    }


    protected int calculatePercentageModification(UserEntity userEntity, Attribute attribute) {
        return 0;
    }

    public SimpleValueAttributeCalculationResult calculateActualBeforePercentageMultiplication(UserEntity user, Attribute attribute) {
        final SimpleValueAttributeCalculationResult result = attributeCalculationResultFactory.newResult(attribute);

        for (AttributeBonusProvider attributeBonusProvider : attributeBonusProviders) {
            attributeBonusProvider.calculateBonus(user, attribute).ifPresent(result::addCalculationResult);
        }

        return result;
    }

    public SimpleValueAttributeCalculationResult calculatePercentageModifiedAttribute(SimpleValueAttributeCalculationResult attributeValue, int percentage) {
        attributeValue.setValue((int) (attributeValue.getValue() * ((double) percentage / 100 + 1)));

        return attributeValue;
    }

    public SimpleValueAttributeCalculationResult calculateMaximumValue(UserEntity user, Attribute attribute) {
        if (attribute.isUnlimited()) {
            return new UnlimitedValueAttributeCalculationResult(attribute);
        }

        final int racialModifier = calculatePercentageModification(user, attribute);

        return calculatePercentageModifiedAttribute(calculateMaximumBeforePercentageMultiplication(user, attribute), racialModifier);
    }

    public SimpleValueAttributeCalculationResult calculateMaximumBeforePercentageMultiplication(UserEntity userEntity, Attribute attribute) {
        final SimpleValueAttributeCalculationResult result = attributeCalculationResultFactory.newResult(attribute);

        result.increaseValue(attribute.getInitialValue());

        for (AttributeBonusProvider attributeBonusProvider : attributeBonusProviders) {
            attributeBonusProvider.calculateBonus(userEntity, attribute).ifPresent(result::addCalculationResult);
        }

        return result;
    }

    //TODO: What is this??? Nowhere used??? Why???
    protected void checkMinimumValue(final Attribute attribute, final SimpleValueAttributeCalculationResult attributeCalculationResult) {
    }
}
