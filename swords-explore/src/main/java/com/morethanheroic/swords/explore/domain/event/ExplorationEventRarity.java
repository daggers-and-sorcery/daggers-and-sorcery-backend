package com.morethanheroic.swords.explore.domain.event;

public enum ExplorationEventRarity {
    COMMON(4), UNCOMMON(3), RARE(2), EPIC(1);

    private final int chance;

    ExplorationEventRarity(final int chance) {
        this.chance = chance;
    }

    /**
     * How many times this event should be added to the random pool of events. Eg 1 means once and 4 means four times so the pool
     * will be (1, 4, 4, 4, 4) and when we draw randomly we have 20% chance for the event with the id 1 and 80% for the event with
     * the id 4.
     */
    public int getChance() {
        return chance;
    }
}
