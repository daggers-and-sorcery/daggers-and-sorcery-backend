package com.morethanheroic.swords.attribute.service.modifier.domain;

import com.morethanheroic.swords.attribute.domain.type.AttributeModifierType;
import com.morethanheroic.swords.attribute.domain.type.AttributeModifierUnitType;

/**
 * Contains the data for one modifier type. (Eg.: all bonuses to this attribute gained from equipments or skills or buffs etc.
 */
public class AttributeModifierEntry {

    private final AttributeModifierType attributeModifierType;
    private final AttributeModifierUnitType attributeModifierUnitType;
    private final AttributeModifierValue attributeModifierValue;

    public AttributeModifierEntry(AttributeModifierType attributeModifierType, AttributeModifierUnitType attributeModifierUnitType, AttributeModifierValue attributeModifierValue) {
        this.attributeModifierType = attributeModifierType;
        this.attributeModifierUnitType = attributeModifierUnitType;
        this.attributeModifierValue = attributeModifierValue;
    }

    public AttributeModifierType getAttributeModifierType() {
        return attributeModifierType;
    }

    public AttributeModifierUnitType getAttributeModifierUnitType() {
        return attributeModifierUnitType;
    }

    public AttributeModifierValue getAttributeModifierValue() {
        return attributeModifierValue;
    }
}
