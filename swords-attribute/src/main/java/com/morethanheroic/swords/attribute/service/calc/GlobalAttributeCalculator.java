package com.morethanheroic.swords.attribute.service.calc;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.service.calc.domain.calculation.AttributeCalculationResult;
import com.morethanheroic.swords.attribute.service.calc.domain.data.AttributeData;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This calculator is used when you don't know the attribute's type or it doesn't matter for you. It will automatically
 * delegate the calls to the appropriate {@link AttributeCalculator}.
 */
@Service
public class GlobalAttributeCalculator {

    @Autowired
    private AttributeCalculatorLocator attributeCalculatorLocator;

    @SuppressWarnings("unchecked")
    public AttributeData calculateAttributeValue(UserEntity user, Attribute attribute) {
        return attributeCalculatorLocator.getCalculator(attribute).calculateAttributeValue(user, attribute);
    }

    public AttributeCalculationResult calculateActualValue(UserEntity user, Attribute attribute) {
        return calculateActualValue(user, attribute, true);
    }

    public AttributeCalculationResult calculateActualValue(UserEntity user, Attribute attribute, boolean shouldCheckMinimum) {
        return attributeCalculatorLocator.getCalculator(attribute).calculateActualValue(user, attribute, shouldCheckMinimum);
    }

    public AttributeCalculationResult calculateMaximumValue(UserEntity user, Attribute attribute) {
        return attributeCalculatorLocator.getCalculator(attribute).calculateMaximumValue(user, attribute);
    }
}
