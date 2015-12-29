package com.morethanheroic.swords.attribute.domain;

import com.morethanheroic.swords.attribute.domain.type.AttributeType;

/**
 * Hold the data of an attribute in the game.
 */
public interface Attribute {

    AttributeType getAttributeType();

    int getInitialValue();

    boolean isUnlimited();

    String getName();
}
