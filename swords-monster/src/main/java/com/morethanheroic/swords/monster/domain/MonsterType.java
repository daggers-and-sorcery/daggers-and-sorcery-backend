package com.morethanheroic.swords.monster.domain;

import org.apache.commons.text.WordUtils;

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
    VAMPIRE,
    DEMON,
    DRAGON;

    public String getName() {
        return WordUtils.capitalize(name().toLowerCase().replace("_", " "));
    }
}
