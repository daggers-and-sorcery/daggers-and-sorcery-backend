package com.swordssorcery.server.game.attribute.data;

import com.swordssorcery.server.game.attribute.AttributeModifierType;
import com.swordssorcery.server.game.attribute.AttributeModifierValueType;

public class AttributeModifierData {

    private final AttributeModifierType attributeModifierType;
    private final AttributeModifierValueType attributeModifierValueType;
    private final int attributeModifierValue;
    private final int modifierExtraData;

    public AttributeModifierData(AttributeModifierType attributeModifierType, AttributeModifierValueType attributeModifierValueType, int attributeModifierValue) {
        this(attributeModifierType, attributeModifierValueType, attributeModifierValue, 0);
    }

    public AttributeModifierData(AttributeModifierType attributeModifierType, AttributeModifierValueType attributeModifierValueType, int attributeModifierValue, int modifierExtraData) {
        this.attributeModifierType = attributeModifierType;
        this.attributeModifierValueType = attributeModifierValueType;
        this.attributeModifierValue = attributeModifierValue;
        this.modifierExtraData = modifierExtraData;
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

    public int getModifierExtraData() {
        return modifierExtraData;
    }
}
