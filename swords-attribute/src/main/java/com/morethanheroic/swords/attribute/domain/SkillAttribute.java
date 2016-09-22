package com.morethanheroic.swords.attribute.domain;

import com.morethanheroic.swords.attribute.domain.type.AttributeType;

/**
 * The {@link AttributeType#SKILL} attributes.
 */
public enum SkillAttribute implements Attribute {

    TWO_HANDED_CRUSHING_WEAPONS,
    ONE_HANDED_CRUSHING_WEAPONS,
    TWO_HANDED_AXES,
    ONE_HANDED_AXES,
    THROWING_WEAPONS,
    FISTFIGHT,
    LONGSWORDS,
    SHORTSWORDS,
    POLEARMS,
    DAGGERS,
    LONGBOWS,
    SHORTBOWS,
    CROSSBOWS,
    LIGHT_ARMOR,
    HEAVY_ARMOR,
    ROBE_ARMOR,
    ARMORLESS_DEFENSE,
    SHIELD_DEFENSE,
    STAFF,
    WAND,
    SPECTRE,
    SCAVENGING,
    COOKING,
    LEATHERWORKING,
    SMITHING,
    FOCUS,
    DESTRUCTION,
    RESTORATION,
    ALTERATION,
    LOCKPICKING;

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
        throw new UnsupportedOperationException("SkillAttribute names must be accessed from SkillDefinitions!");
    }
}
