package com.morethanheroic.swords.race.model;

import com.morethanheroic.swords.race.model.modifier.RacialModifier;
import com.morethanheroic.swords.race.model.modifier.entry.RacialModifierEntry;
import lombok.Builder;

import java.util.Map;

/**
 * Store the static data of a race in the game.
 */
@Builder
public class RaceEntity {

    private final Race race;
    private final String name;
    private final Map<RacialModifier, RacialModifierEntry> racialModifierEntryMap;

    public boolean hasRacialModifier(RacialModifier racialModifier) {
        return racialModifierEntryMap.containsKey(racialModifier);
    }

    public RacialModifierEntry getRacialModifier(RacialModifier racialModifier) {
        return racialModifierEntryMap.get(racialModifier);
    }
}
