package com.morethanheroic.swords.attribute.enums;

import com.morethanheroic.swords.attribute.enums.AttributeType;

public interface Attribute {

    AttributeType getAttributeType();

    int getInitialValue();

    boolean isUnlimited();

    String getName();
}
