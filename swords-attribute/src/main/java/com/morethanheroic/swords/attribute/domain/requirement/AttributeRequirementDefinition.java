package com.morethanheroic.swords.attribute.domain.requirement;

import com.morethanheroic.swords.attribute.domain.Attribute;

public abstract class AttributeRequirementDefinition {

    protected int amount;

    public int getAmount() {
        return amount;
    }

    public abstract Attribute getAttribute();
}
