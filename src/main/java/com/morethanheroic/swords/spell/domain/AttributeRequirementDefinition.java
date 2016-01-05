package com.morethanheroic.swords.spell.domain;

import com.morethanheroic.swords.attribute.domain.Attribute;

//TODO: remove this when it's refactored from the spell module
public abstract class AttributeRequirementDefinition {

    protected int amount;

    public int getAmount() {
        return amount;
    }

    public abstract Attribute getAttribute();
}
