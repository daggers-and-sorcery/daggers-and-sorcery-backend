package com.swordssorcery.server.game.attribute.type;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.swordssorcery.server.game.attribute.Attribute;
import com.swordssorcery.server.game.attribute.enums.AttributeType;
import com.swordssorcery.server.game.attribute.enums.GeneralAttributeType;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum GeneralAttribute implements Attribute {

    STRENGTH(10, true, GeneralAttributeType.PHYSICAL),
    PERCEPTION(10, true, GeneralAttributeType.PHYSICAL),
    DEXTERITY(10, true, GeneralAttributeType.PHYSICAL),
    SWIFTNESS(10, true, GeneralAttributeType.PHYSICAL),
    VITALITY( 10, true, GeneralAttributeType.PHYSICAL),
    ENDURANCE(10, true, GeneralAttributeType.PHYSICAL),
    BEAUTY( 10, true, GeneralAttributeType.PHYSICAL),

    INTELLIGENCE(10, true, GeneralAttributeType.MENTAL),
    WISDOM(10, true, GeneralAttributeType.MENTAL),
    WILLPOWER(10, true, GeneralAttributeType.MENTAL),
    CHARISMA(10, true, GeneralAttributeType.MENTAL);

    private final int initialValue;
    private final boolean unlimited;
    private final GeneralAttributeType attributeType;

    GeneralAttribute(int initialValue, boolean unlimited, GeneralAttributeType attributeType) {
        this.initialValue = initialValue;
        this.unlimited = unlimited;
        this.attributeType = attributeType;
    }

    @Override
    public AttributeType getAttributeType() {
        return AttributeType.GENERAL;
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

    public GeneralAttributeType getGeneralAttributeType() {
        return attributeType;
    }
}