package com.morethanheroic.swords.attribute.domain;

import com.morethanheroic.swords.attribute.domain.type.AttributeType;
import lombok.RequiredArgsConstructor;

/**
 * The {@link AttributeType#BASIC} attributes.
 */
@RequiredArgsConstructor
public enum BasicAttribute implements Attribute {

    //TODO: Load these values from an xml file and use a BasicAttributeDefinition class.
    MOVEMENT(30, false);

    private final int initialValue;
    private final boolean unlimited;

    @Override
    public AttributeType getAttributeType() {
        return AttributeType.BASIC;
    }

    @Override
    public int getInitialValue() {
        return initialValue;
    }

    @Override
    public boolean isUnlimited() {
        return unlimited;
    }

    @Override
    public String getName() {
        return this.name();
    }
}
