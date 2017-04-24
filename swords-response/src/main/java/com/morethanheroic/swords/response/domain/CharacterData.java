package com.morethanheroic.swords.response.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Holds the actual data of a character.
 */
@Getter
@Builder
public class CharacterData {

    private final int actualMovement;
    private final int maximumMovement;

    private final int actualHealth;
    private final int maximumHealth;

    private final int actualMana;
    private final int maximumMana;

    /**
     * Shows that the prelude screen is shown to the user and the user choose a starting equipment. A filter on the
     * other side will always show the starting screen until the used choose a starting equipment.
     */
    private final boolean preludeShown;
}
