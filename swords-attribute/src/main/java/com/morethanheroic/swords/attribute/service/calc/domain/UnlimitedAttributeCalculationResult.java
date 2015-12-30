package com.morethanheroic.swords.attribute.service.calc.domain;

import com.morethanheroic.swords.attribute.domain.Attribute;

/**
 * This calculation result is used when the result of the calculation is unlimited for some reasons. (Like most of the attributes in
 * {@link com.morethanheroic.swords.attribute.domain.type.AttributeType#GENERAL}.
 */
public class UnlimitedAttributeCalculationResult extends AttributeCalculationResult {

    public UnlimitedAttributeCalculationResult(Attribute attribute) {
        super(attribute);
    }
}
