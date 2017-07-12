package com.morethanheroic.swords.monster.domain;

import org.apache.commons.lang.WordUtils;

public enum MonsterType {

    UNDEAD,
    SKELETON,
    HUMANOID,
    GOBLIN,
    ZOMBIE,
    ORC,
    HUMAN,
    GNOLL,
    ANIMAL,
    BEAST,
    GHOUL,
    VAMPIRE;

    public String getName() {
        return WordUtils.capitalize(name().toLowerCase().replace("_", " "));
    }
}
