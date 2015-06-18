package com.swordssorcery.server.game.attribute;

public enum Attribute {

    MOVEMENT(AttributeType.BASIC, 30, "movement", false),

    MANA(AttributeType.COMBAT, 30, "mana", false),
    LIFE(AttributeType.COMBAT, 30, "life", false),
    INITIATION(AttributeType.COMBAT, 10, "initiation", true),
    ATTACK(AttributeType.COMBAT, 10, "attack", true),
    AIMING(AttributeType.COMBAT, 10, "aiming", true),
    DEFENSE(AttributeType.COMBAT, 10, "defense", true),
    SPELL_RESISTANCE(AttributeType.COMBAT, 10, "spell_resistance", true),
    DAMAGE(AttributeType.COMBAT, 1, "damage", true),
    RANGED_DAMAGE(AttributeType.COMBAT, 1, "ranged_damage", true),

    STRENGTH(AttributeType.GENERAL_PHYSICAL, 10, "strength", true),
    PERCEPTION(AttributeType.GENERAL_PHYSICAL, 10, "perception", true),
    DEXTERITY(AttributeType.GENERAL_PHYSICAL, 10, "dexterity", true),
    SWIFTNESS(AttributeType.GENERAL_PHYSICAL, 10, "swiftness", true),
    VITALITY(AttributeType.GENERAL_PHYSICAL, 10, "vitality", true),
    ENDURANCE(AttributeType.GENERAL_PHYSICAL, 10, "endurance", true),
    BEAUTY(AttributeType.GENERAL_PHYSICAL, 10, "beauty", true),

    INTELLIGENCE(AttributeType.GENERAL_MENTAL, 10, "intelligence", true),
    WISDOM(AttributeType.GENERAL_MENTAL, 10, "wisdom", true),
    WILLPOWER(AttributeType.GENERAL_MENTAL, 10, "willpower", true),
    CHARISMA(AttributeType.GENERAL_MENTAL, 10, "charisma", true);

    public static final int UNLIMITED_PLACEHOLDER = 0;

    private final AttributeType attributeType;
    private final int initialValue;
    private final String shortName;
    private final boolean unlimited;

    Attribute(AttributeType attributeType, int initialValue, String shortName, boolean unlimited) {
        this.attributeType = attributeType;
        this.initialValue = initialValue;
        this.shortName = shortName;
        this.unlimited = unlimited;
    }

    public AttributeType getAttributeType() {
        return attributeType;
    }

    public int getInitialValue() {
        return initialValue;
    }

    public String getShortName() {
        return shortName;
    }

    public boolean isUnlimited() {
        return unlimited;
    }
}
