package com.morethanheroic.swords.race.model;

/**
 * Holds the static data of the races in the game.
 */
public enum Race {
    HUMAN, ORC, DWARF, ELF, DARK_ELF, LIZARDMEN, GNOME, DRACONIC

    //These are available from the RaceEntity

    /*
    private final Map<RacialModifier, RacialModifierEntry> racialAttributeModifiers = new LinkedHashMap<>();

    private void addRacialModifier(final NumericRacialModifierEntry numericRacialModifierEntry) {
        racialAttributeModifiers.put(numericRacialModifierEntry.getType(), numericRacialModifierEntry);
    }

    public RacialModifierEntry getRacialModifier(RacialModifier attribute) {
        return racialAttributeModifiers.get(attribute);
    }

    public String getName() {
        return this.name();
    }
    */
}
