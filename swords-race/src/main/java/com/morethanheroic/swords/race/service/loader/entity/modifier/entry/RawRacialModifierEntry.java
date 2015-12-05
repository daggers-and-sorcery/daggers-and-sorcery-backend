package com.morethanheroic.swords.race.service.loader.entity.modifier.entry;

import com.morethanheroic.swords.race.model.modifier.RacialModifier;

/**
 * Olds the loaded xml data of {@link RawRacialModifierEntry}.
 */
public interface RawRacialModifierEntry {

    RacialModifier getRacialModifier();

    int getValue();
}
