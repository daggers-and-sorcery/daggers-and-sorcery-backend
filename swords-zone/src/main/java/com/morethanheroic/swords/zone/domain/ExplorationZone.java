package com.morethanheroic.swords.zone.domain;

/**
 * Contains the possible location of exploration events.
 */
public enum ExplorationZone {

    /**
     * Usually used by instances or quests. Not assignable in the normal ways (eg by the explore menu).
     */
    UNKNOWN,

    /**
     * First location in Sevgard.
     */
    FARMFIELDS,

    /**
     * Second location in Sevgard.
     */
    WHISPERING_WOODS,

    /**
     * Third location in Sevgard.
     */
    WOODLAND_CEMETERY,

    /**
     * Fourth location in Sevgard. Only accessible with a quest and only 3 times daily.
     */
    TOMB_OF_THE_CURSED
}
