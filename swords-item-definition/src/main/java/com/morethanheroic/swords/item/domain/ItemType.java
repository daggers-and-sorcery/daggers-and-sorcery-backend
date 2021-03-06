package com.morethanheroic.swords.item.domain;

import org.apache.commons.text.WordUtils;

/**
 * Contains the available types of an item. the type decides for example which equipment spot does the item go on when
 * equiped etc.
 */
public enum ItemType {

    TWO_HANDED_CRUSHING_WEAPONS,
    ONE_HANDED_CRUSHING_WEAPONS,
    TWO_HANDED_AXES,
    ONE_HANDED_AXES,
    THROWING_WEAPONS,
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
    SHIELD,
    STAFF,
    WAND,
    SCEPTRE,
    MONEY,
    ARROW,
    MATERIAL,
    TOOL,
    RECIPE,
    FOOD,
    SPELL_TOME,
    AMULET,
    GLOVES,
    BOOTS,
    HELM,
    LIQUID,
    PLATELEGS,
    FOCUS,
    ARMOR,
    WEAPON,
    PROJECTILE,
    JEWELRY,
    BELT,
    POTION,
    STOCKINGS,
    LEGGINGS,
    HERB,
    CLOTH,
    RING,
    FABRIC,
    DYE;

    public String getName() {
        return WordUtils.capitalize(name().toLowerCase().replace("_", " "));
    }
}
