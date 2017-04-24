package com.morethanheroic.swords.attribute.service.calc;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.service.calc.domain.calculation.AttributeCalculationResult;
import com.morethanheroic.swords.attribute.service.calc.domain.data.AttributeData;
import com.morethanheroic.swords.user.domain.UserEntity;

/**
 * Attribute calculators used to calculate all data about a give attribute for a user.
 *
 * @param <T> The {@link com.morethanheroic.swords.attribute.domain.type.AttributeType} of the attribute this calculator belongs to.
 */
public interface AttributeCalculator<T extends Attribute> {

    AttributeData calculateAttributeValue(UserEntity user, T attribute);

    AttributeCalculationResult calculateActualValue(UserEntity user, Attribute attribute, boolean shouldCheckMinimum);

    AttributeCalculationResult calculateMaximumValue(UserEntity user, Attribute attribute);

    AttributeCalculationResult calculateActualBeforePercentageMultiplication(UserEntity user, Attribute attribute);

    Class<T> getSupportedType();
}
