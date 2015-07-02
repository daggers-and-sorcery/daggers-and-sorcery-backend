package com.swordssorcery.server.game.attribute.data;

import com.swordssorcery.server.game.attribute.enums.AttributeModifierType;
import com.swordssorcery.server.game.attribute.enums.AttributeModifierValueType;

public class PercentageAttributeModifierData extends AttributeModifierData {

    private final int percentageBonus;

    public PercentageAttributeModifierData(AttributeModifierType attributeModifierType, AttributeModifierValueType attributeModifierValueType, int attributeModifierValue, int percentageBonus) {
        super(attributeModifierType, attributeModifierValueType, attributeModifierValue);

        this.percentageBonus = percentageBonus;
    }

    public int getPercentageBonus() {
        return percentageBonus;
    }
}
