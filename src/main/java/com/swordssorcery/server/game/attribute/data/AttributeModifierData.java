package com.swordssorcery.server.game.attribute.data;

import com.swordssorcery.server.game.attribute.AttributeModifierType;
import com.swordssorcery.server.game.attribute.AttributeModifierValueType;

public interface AttributeModifierData {

    AttributeModifierType getAttributeModifierType();
    AttributeModifierValueType getAttributeModifierValueType();
    int getAttributeModifierValue();
}
