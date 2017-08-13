package com.morethanheroic.swords.item.domain.requirement;

import org.apache.commons.lang.WordUtils;

/**
 * Contains the type of requirements an item can have. If all of the requirements not met then the user can't equip
 * the item (eg: 8 strength needed to equip or 30 attack to wear).
 */
public enum ItemRequirement {

    //Basic
    MOVEMENT,

    //Combat
    MANA,
    LIFE,
    INITIATION,
    ATTACK,
    MAGIC_ATTACK,
    MAGIC_DAMAGE,
    AIMING,
    DEFENSE,
    SPELL_RESISTANCE,
    DAMAGE,
    RANGED_DAMAGE,

    //General
    STRENGTH,
    PERCEPTION,
    DEXTERITY,
    SWIFTNESS,
    VITALITY,
    ENDURANCE,
    BEAUTY,
    INTELLIGENCE,
    WISDOM,
    WILLPOWER,
    CHARISMA,

    //Skill
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
    SCEPTRE,
    SCAVENGING,
    COOKING,
    FOCUS;

    public String getName() {
        return WordUtils.capitalize(name().toLowerCase().replace("_", " "));
    }
}
