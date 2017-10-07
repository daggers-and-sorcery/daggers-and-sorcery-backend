package com.morethanheroic.swords.race.model.modifier.entry;

import com.morethanheroic.swords.race.model.modifier.RacialModifier;
import lombok.RequiredArgsConstructor;

/**
 * An {@link Integer} based representation of a {@link RacialModifierEntry}.
 */
@RequiredArgsConstructor
public class NumericRacialModifierEntry implements RacialModifierEntry<Integer> {

    private final RacialModifier racialModifier;
    private final int value;

    @Override
    public RacialModifier getType() {
        return racialModifier;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
