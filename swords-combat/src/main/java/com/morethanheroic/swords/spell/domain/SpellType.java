package com.morethanheroic.swords.spell.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SpellType {

    DESTRUCTION("Destruction"),
    DIVINE("Divine"),
    ALTERATION("Alteration"),
    RESTORATION("Restoration");

    private final String name;
}
