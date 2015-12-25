package com.morethanheroic.swords.item.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ItemModifierDefinition {

    private final ItemModifier modifier;
    private final int amount;
    private final int d2;
    private final int d4;
    private final int d6;
    private final int d8;
    private final int d10;
}
