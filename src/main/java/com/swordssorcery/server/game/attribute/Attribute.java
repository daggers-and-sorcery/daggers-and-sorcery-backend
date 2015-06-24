package com.swordssorcery.server.game.attribute;

public interface Attribute {

    AttributeType getAttributeType();

    int getInitialValue();

    boolean isUnlimited();

    String getName();
}
