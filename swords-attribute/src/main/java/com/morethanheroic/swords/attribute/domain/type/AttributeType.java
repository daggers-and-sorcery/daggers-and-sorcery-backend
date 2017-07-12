package com.morethanheroic.swords.attribute.domain.type;

/**
 * The main types of attributes.
 */
public enum AttributeType {

    /**
     * The most basic attributes of an user that doesn't fit into any other categories like movement point or scavenging bonus etc.
     */
    BASIC,

    /**
     * The combat attributes of an user.
     */
    COMBAT,

    /**
     * The general attributes are the attributes like strength, beauty and so on.
     */
    GENERAL,

    /**
     * Skill attributes are the skills of the character like fistfighting or cooking.
     */
    SKILL,

    /**
     * Special attributes are attributes that doesn't always displayed to the user like extra/periodical damage against various
     * types of monsters and so on.
     */
    SPECIAL
}
