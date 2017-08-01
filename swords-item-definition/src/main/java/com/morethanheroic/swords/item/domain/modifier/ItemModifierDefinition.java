package com.morethanheroic.swords.item.domain.modifier;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * Contains the definitions of an item modifier on an item.
 */
@Builder
@Getter
@ToString
public class ItemModifierDefinition {

    private final ItemModifier modifier;
    private final int amount;
    private final int d2;
    private final int d4;
    private final int d6;
    private final int d8;
    private final int d10;
}
