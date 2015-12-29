package com.morethanheroic.swords.attribute.service.modifier.domain;

import com.morethanheroic.swords.attribute.domain.type.AttributeModifierType;
import com.morethanheroic.swords.attribute.domain.type.AttributeModifierUnitType;

public class PercentageAttributeModifierEntry extends AttributeModifierEntry {

    private final int percentageBonus;

    public PercentageAttributeModifierEntry(AttributeModifierType attributeModifierType, AttributeModifierUnitType attributeModifierUnitType, AttributeModifierValue attributeModifierValue, int percentageBonus) {
        super(attributeModifierType, attributeModifierUnitType, attributeModifierValue);

        this.percentageBonus = percentageBonus;
    }

    public int getPercentageBonus() {
        return percentageBonus;
    }
}
