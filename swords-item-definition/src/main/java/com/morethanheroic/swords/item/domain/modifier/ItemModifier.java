package com.morethanheroic.swords.item.domain.modifier;

import org.apache.commons.text.WordUtils;

/**
 * Contains the types of the modifier an item can have. The modifiers are modify the attribute of the user somehow
 * (eg. giving the more strength or reducing it's attack by a given amount or percentage).
 */
public enum ItemModifier {

    //Basic
    MOVEMENT,
    SCAVENGING_BONUS,

    //Combat
    MANA,
    LIFE,
    INITIATION,
    ATTACK,
    MAGIC_ATTACK,
    MAGIC_DAMAGE,
    RANGED_ATTACK,
    AIMING,
    DEFENSE,
    DAMAGE_REDUCTION,
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

    //Special
    LIFE_STEAL,
    EXTRA_DAMAGE_AGAINST_VAMPIRES,
    PERIODICAL_DAMAGE_AGAINST_VAMPIRES,
    PERIODICAL_DAMAGE_AGAINST_UNDEAD;

    public String getName() {
        return WordUtils.capitalize(name().toLowerCase().replace("_", " "));
    }
}
