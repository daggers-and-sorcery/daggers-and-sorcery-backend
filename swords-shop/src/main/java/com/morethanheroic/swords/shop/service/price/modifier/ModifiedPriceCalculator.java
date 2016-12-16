package com.morethanheroic.swords.shop.service.price.modifier;

import com.morethanheroic.swords.shop.service.price.modifier.domain.ItemPriceModifierType;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Calculate the final price of an item after applying all modifiers.
 */
@Service
public class ModifiedPriceCalculator {

    /**
     * Calculate the final price of an item after applying all of the provided modifiers on the provided base price.
     *
     * @param basePrice the base price of an item
     * @param modifiers the price modifiers
     * @return the final price after all modifiers are applied
     */
    public int calculateFinalPrice(final int basePrice, final Map<ItemPriceModifierType, Double> modifiers) {
        return (int) Math.round(
                (basePrice + modifiers.getOrDefault(ItemPriceModifierType.VALUE, 0D)) * (1 + modifiers.getOrDefault(ItemPriceModifierType.PERCENTAGE, 0D))
        );
    }
}
