package com.morethanheroic.swords.shop.service.price.modifier.domain;

import lombok.Builder;
import lombok.Getter;

/**
 * Represent a modification on the final result of the price calculation. The result of the calculation will be
 * modified by the given value.
 */
@Getter
@Builder
public class ItemPriceModifier {

    private final double value;
    private final ItemPriceModifierType modifierType;
}
