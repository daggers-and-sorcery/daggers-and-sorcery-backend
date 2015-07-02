package com.swordssorcery.server.game.attribute.type;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.swordssorcery.server.game.attribute.Attribute;
import com.swordssorcery.server.game.attribute.enums.AttributeType;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum SkillAttribute implements Attribute {

    TWO_HANDED_CRUSHING_WEAPONS,ONE_HANDED_CRUSHING_WEAPONS, ONE_HANDED_AXES,
    TWO_HANDED_AXES, HERBLORE, WOODCUTTING, ALCHEMY;

    @Override
    public AttributeType getAttributeType() {
        return AttributeType.SKILL;
    }

    @Override
    public int getInitialValue() {
        return 1;
    }

    @Override
    public boolean isUnlimited() {
        return true;
    }

    @Override
    public String getName() {
        return this.name();
    }
}
