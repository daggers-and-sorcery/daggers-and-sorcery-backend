package com.morethanheroic.swords.attribute.service.modifier.domain;

import com.morethanheroic.swords.attribute.domain.type.AttributeModifierType;
import com.morethanheroic.swords.attribute.domain.type.AttributeModifierValueType;
import com.morethanheroic.swords.attribute.service.calc.domain.AttributeModifierValue;
import com.morethanheroic.swords.attribute.service.modifier.domain.AttributeModifierData;

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
