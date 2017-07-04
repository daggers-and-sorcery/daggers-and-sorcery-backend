package com.morethanheroic.swords.attribute.service.calc.domain.calculation;

import com.morethanheroic.swords.attribute.domain.Attribute;

/**
 * This calculation result is used when the result of the calculation is unlimited for some reasons. (Like most of the attributes in
 * {@link com.morethanheroic.swords.attribute.domain.type.AttributeType#GENERAL}.
 */
public class UnlimitedValueAttributeCalculationResult extends SimpleValueAttributeCalculationResult {

    public UnlimitedValueAttributeCalculationResult(Attribute attribute) {
        super(attribute);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
