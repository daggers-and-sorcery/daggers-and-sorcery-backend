package com.morethanheroic.swords.skill.domain;

import static org.apache.commons.lang3.text.WordUtils.capitalizeFully;

/**
 * List of the available skills in the game.
 */
//TODO: Create a definition loader like in race.
public enum SkillType {

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
    SMITHING;

    public String getName() {
        //TODO: Get this from definition if definition handling is exist.
        return capitalizeFully(name().replace("_", " "));
    }
}
