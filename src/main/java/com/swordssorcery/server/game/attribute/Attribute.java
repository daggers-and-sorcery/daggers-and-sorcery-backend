package com.swordssorcery.server.game.attribute;

import com.swordssorcery.server.game.attribute.enums.AttributeType;

public interface Attribute {

    AttributeType getAttributeType();

    int getInitialValue();

    boolean isUnlimited();

    String getName();
}
