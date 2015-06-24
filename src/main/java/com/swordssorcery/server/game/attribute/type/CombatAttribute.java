package com.swordssorcery.server.game.attribute.type;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.swordssorcery.server.game.attribute.Attribute;
import com.swordssorcery.server.game.attribute.AttributeType;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum CombatAttribute implements Attribute {

    MANA(30, false),
    LIFE(30, false),
    INITIATION(10, true),
    ATTACK(10, true),
    AIMING(10, true),
    DEFENSE(10, true),
    SPELL_RESISTANCE(10, true),
    DAMAGE(1, true),
    RANGED_DAMAGE(1, true);

    private final int initialValue;
    private final boolean unlimited;

    CombatAttribute(int initialValue, boolean unlimited) {
        this.initialValue = initialValue;
        this.unlimited = unlimited;
    }

    @Override
    public AttributeType getAttributeType() {
        return AttributeType.COMBAT;
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
