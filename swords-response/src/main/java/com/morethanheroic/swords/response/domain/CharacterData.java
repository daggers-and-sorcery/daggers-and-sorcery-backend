package com.morethanheroic.swords.response.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Holds the actual data of a character.
 */
@Getter
@RequiredArgsConstructor
public class CharacterData {

    private final int movement;
    private final int health;
    private final int mana;
}
