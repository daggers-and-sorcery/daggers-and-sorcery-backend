package com.morethanheroic.swords.shop.service.price.modifier.domain;

/**
 * Shows that what's the type of the modifier.
 */
public enum ItemPriceModifierType {

    /**
     * Value modifiers are additive to the final calculation result.
     */
    VALUE,
    /**
     * Percentage modifiers are multiplicative to the final result.
     */
    PERCENTAGE
}
