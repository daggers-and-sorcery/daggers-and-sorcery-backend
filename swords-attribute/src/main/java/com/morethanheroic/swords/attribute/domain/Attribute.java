package com.morethanheroic.swords.attribute.domain;

import com.morethanheroic.swords.attribute.domain.type.AttributeType;

public interface Attribute {

    AttributeType getAttributeType();

    int getInitialValue();

    boolean isUnlimited();

    String getName();
}
