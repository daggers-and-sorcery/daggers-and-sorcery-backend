package com.morethanheroic.swords.skill.domain;

import static org.apache.commons.lang3.text.WordUtils.capitalizeFully;

/**
 * List of the available skills in the game.
 */
//TODO: Create a definition loader like in race.
//TODO: Maybe we should remove this enum altogether and use SkillAttribute instead? That sounds like the wisest choice.
//This is however only possible after we moved the skillgroups into a definition file.
public enum SkillType {

    TWO_HANDED_CRUSHING_WEAPONS(SkillGroup.COMBAT),
    ONE_HANDED_CRUSHING_WEAPONS(SkillGroup.COMBAT),
    TWO_HANDED_AXES(SkillGroup.COMBAT),
    ONE_HANDED_AXES(SkillGroup.COMBAT),
    THROWING_WEAPONS(SkillGroup.COMBAT),
    FISTFIGHT(SkillGroup.COMBAT),
    LONGSWORDS(SkillGroup.COMBAT),
    SHORTSWORDS(SkillGroup.COMBAT),
    POLEARMS(SkillGroup.COMBAT),
    DAGGERS(SkillGroup.COMBAT),
    LONGBOWS(SkillGroup.COMBAT),
    SHORTBOWS(SkillGroup.COMBAT),
    CROSSBOWS(SkillGroup.COMBAT),
    LIGHT_ARMOR(SkillGroup.COMBAT),
    HEAVY_ARMOR(SkillGroup.COMBAT),
    ROBE_ARMOR(SkillGroup.COMBAT),
    ARMORLESS_DEFENSE(SkillGroup.COMBAT),
    SHIELD_DEFENSE(SkillGroup.COMBAT),
    STAFF(SkillGroup.COMBAT),
    WAND(SkillGroup.COMBAT),
    FOCUS(SkillGroup.COMBAT),
    SPECTRE(SkillGroup.COMBAT),
    SCAVENGING(SkillGroup.TRADE),
    COOKING(SkillGroup.TRADE),
    LEATHERWORKING(SkillGroup.TRADE),
    SMITHING(SkillGroup.TRADE),
    FLETCHING(SkillGroup.TRADE),
    HERBLORE(SkillGroup.TRADE),
    DESTRUCTION(SkillGroup.MAGIC),
    RESTORATION(SkillGroup.MAGIC),
    ALTERATION(SkillGroup.MAGIC),
    LOCKPICKING(SkillGroup.SHADOW);

    private final SkillGroup skillGroup;

    SkillType(final SkillGroup skillGroup) {
        this.skillGroup = skillGroup;
    }

    public SkillGroup getSkillGroup() {
        return skillGroup;
    }

    /**
     * @deprecated Use SkillAttributeDefinition#getName().
     */
    @Deprecated
    public String getName() {
        return capitalizeFully(name().replace("_", " "));
    }
}
