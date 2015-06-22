package com.swordssorcery.server.game.attribute;

public enum Attribute {

    MOVEMENT(AttributeType.BASIC, 30, false),

    MANA(AttributeType.COMBAT, 30, false),
    LIFE(AttributeType.COMBAT, 30, false),
    INITIATION(AttributeType.COMBAT, 10, true),
    ATTACK(AttributeType.COMBAT, 10, true),
    AIMING(AttributeType.COMBAT, 10, true),
    DEFENSE(AttributeType.COMBAT, 10, true),
    SPELL_RESISTANCE(AttributeType.COMBAT, 10, true),
    DAMAGE(AttributeType.COMBAT, 1, true),
    RANGED_DAMAGE(AttributeType.COMBAT, 1, true),

    STRENGTH(AttributeType.GENERAL_PHYSICAL, 10, true),
    PERCEPTION(AttributeType.GENERAL_PHYSICAL, 10, true),
    DEXTERITY(AttributeType.GENERAL_PHYSICAL, 10, true),
    SWIFTNESS(AttributeType.GENERAL_PHYSICAL, 10, true),
    VITALITY(AttributeType.GENERAL_PHYSICAL, 10, true),
    ENDURANCE(AttributeType.GENERAL_PHYSICAL, 10, true),
    BEAUTY(AttributeType.GENERAL_PHYSICAL, 10, true),

    INTELLIGENCE(AttributeType.GENERAL_MENTAL, 10, true),
    WISDOM(AttributeType.GENERAL_MENTAL, 10, true),
    WILLPOWER(AttributeType.GENERAL_MENTAL, 10, true),
    CHARISMA(AttributeType.GENERAL_MENTAL, 10, true);

    public static final int NO_MAXIMUM_VALUE = 0;

    private final AttributeType attributeType;
    private final int initialValue;
    private final boolean unlimited;

    Attribute(AttributeType attributeType, int initialValue, boolean unlimited) {
        this.attributeType = attributeType;
        this.initialValue = initialValue;
        this.unlimited = unlimited;
    }

    public AttributeType getAttributeType() {
        return attributeType;
    }

    public int getInitialValue() {
        return initialValue;
    }

    public boolean isUnlimited() {
        return unlimited;
    }
}
