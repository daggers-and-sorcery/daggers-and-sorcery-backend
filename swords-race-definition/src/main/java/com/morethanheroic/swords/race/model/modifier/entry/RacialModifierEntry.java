package com.morethanheroic.swords.race.model.modifier.entry;

import com.morethanheroic.swords.race.model.modifier.RacialModifier;

/**
 * A racial modifier entry. Holds the data of one racial modifier.
 *
 * @param <T> the value of the racial modification
 */
public interface RacialModifierEntry<T> {

    RacialModifier getType();

    T getValue();
}
