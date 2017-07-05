package com.morethanheroic.swords.explore.domain.event;

/**
 * Contains the possible location of exploration events.
 */
public enum ExplorationEventLocation {

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
    WOODLAND_CEMETERY
}
