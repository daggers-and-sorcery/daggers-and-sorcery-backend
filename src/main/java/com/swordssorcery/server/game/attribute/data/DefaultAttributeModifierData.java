package com.swordssorcery.server.game.attribute.data;

import com.swordssorcery.server.game.attribute.AttributeModifierType;

public class DefaultAttributeModifierData implements AttributeModifierData {

    private final AttributeModifierType attributeModifierType;
    private int attributeModifierValue;

    public DefaultAttributeModifierData(AttributeModifierType attributeModifierType, int attributeModifierValue) {
        this.attributeModifierType = attributeModifierType;
        this.attributeModifierValue = attributeModifierValue;
    }

    @Override
    public AttributeModifierType getAttributeModifierType() {
        return attributeModifierType;
    }

    @Override
    public int getAttributeModifierValue() {
        return attributeModifierValue;
    }
}
