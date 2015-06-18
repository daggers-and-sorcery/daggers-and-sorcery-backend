package com.swordssorcery.server.game.attribute.data;

import com.swordssorcery.server.game.attribute.AttributeModifierType;

public interface AttributeModifierData {

    AttributeModifierType getAttributeModifierType();
    int getAttributeModifierValue();
}
