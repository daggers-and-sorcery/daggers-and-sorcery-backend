package com.swordssorcery.server.game.attribute.type;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.swordssorcery.server.game.attribute.Attribute;
import com.swordssorcery.server.game.attribute.AttributeType;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum BasicAttribute implements Attribute {
    MOVEMENT(30, false);

    private final int initialValue;
    private final boolean unlimited;

    BasicAttribute(int initialValue, boolean unlimited) {
        this.initialValue = initialValue;
        this.unlimited = unlimited;
    }

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
