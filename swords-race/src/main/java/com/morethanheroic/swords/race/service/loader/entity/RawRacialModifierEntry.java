package com.morethanheroic.swords.race.service.loader.entity;

import com.morethanheroic.swords.race.model.modifier.RacialModifier;

/**
 * Holds the loaded xml data of {@link RawRacialModifierEntry}.
 */
public interface RawRacialModifierEntry {

    RacialModifier getRacialModifier();

    int getValue();
}
