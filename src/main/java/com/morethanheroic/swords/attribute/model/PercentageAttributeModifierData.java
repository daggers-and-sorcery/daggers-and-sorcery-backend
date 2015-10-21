package com.morethanheroic.swords.attribute.model;

import com.morethanheroic.swords.attribute.enums.AttributeModifierType;
import com.morethanheroic.swords.attribute.enums.AttributeModifierValueType;
import com.morethanheroic.swords.attribute.service.calc.domain.AttributeModifierValue;

public class PercentageAttributeModifierData extends AttributeModifierData {

    private final int percentageBonus;

    public PercentageAttributeModifierData(AttributeModifierType attributeModifierType, AttributeModifierValueType attributeModifierValueType, AttributeModifierValue attributeModifierValue, int percentageBonus) {
        super(attributeModifierType, attributeModifierValueType, attributeModifierValue);

        this.percentageBonus = percentageBonus;
    }

    public int getPercentageBonus() {
        return percentageBonus;
    }
}
