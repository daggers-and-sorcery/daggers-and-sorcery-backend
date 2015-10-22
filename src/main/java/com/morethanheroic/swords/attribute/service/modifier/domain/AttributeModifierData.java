package com.morethanheroic.swords.attribute.service.modifier.domain;

import com.morethanheroic.swords.attribute.domain.type.AttributeModifierType;
import com.morethanheroic.swords.attribute.domain.type.AttributeModifierValueType;
import com.morethanheroic.swords.attribute.service.calc.domain.AttributeModifierValue;

public class AttributeModifierData {

    private final AttributeModifierType attributeModifierType;
    private final AttributeModifierValueType attributeModifierValueType;
    private final AttributeModifierValue attributeModifierValue;

    public AttributeModifierData(AttributeModifierType attributeModifierType, AttributeModifierValueType attributeModifierValueType, AttributeModifierValue attributeModifierValue) {
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

    public AttributeModifierValue getAttributeModifierValue() {
        return attributeModifierValue;
    }
}
