package com.morethanheroic.swords.item.service.domain;

import com.morethanheroic.swords.attribute.enums.Attribute;

public abstract class AttributeModifierDefinition {

    private int amount;

    public int getAmount() {
        return amount;
    }

    public abstract Attribute getAttribute();
}
