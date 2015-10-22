package com.morethanheroic.swords.attribute.domain.modifier;

import com.morethanheroic.swords.attribute.domain.Attribute;

public abstract class AttributeModifierDefinition {

    protected int amount;

    public int getAmount() {
        return amount;
    }

    public abstract Attribute getAttribute();
}
