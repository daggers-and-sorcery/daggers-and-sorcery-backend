package com.swordssorcery.server.game.attribute.data;

import com.swordssorcery.server.game.attribute.enums.AttributeModifierType;
import com.swordssorcery.server.game.attribute.enums.AttributeModifierValueType;

public class AttributeModifierData {

    private final AttributeModifierType attributeModifierType;
    private final AttributeModifierValueType attributeModifierValueType;
    private final int attributeModifierValue;

    public AttributeModifierData(AttributeModifierType attributeModifierType, AttributeModifierValueType attributeModifierValueType, int attributeModifierValue) {
        this.attributeModifierType = attributeModifierType;
        this.attributeModifierValueType = attributeModifierValueType;
        this.attributeModifierValue = attributeModifierValue;
    }

    public AttributeModifierType getAttributeModifierType() {
        return attributeModifierType;
    }

    public AttributeModifierValueType getAttributeModifierValueType() {
        return attributeModifierValueType;
    }

    public int getAttributeModifierValue() {
        return attributeModifierValue;
    }
}
