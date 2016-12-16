package com.morethanheroic.swords.shop.service.price.modifier.domain;

import com.morethanheroic.swords.shop.service.price.modifier.provider.ItemPriceModifierProvider;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Collect the modifications on the item's price.
 */
@Service
public class ItemPriceModificationCollector {

    /**
     * Collect the modifications on the item's price based on the provided providers and modification context.
     * @param itemPriceModifierProviders the providers to collect the modifiers from
     * @param itemPriceModificationContext the context of the modification
     * @return the provided modifications
     */
    public Map<ItemPriceModifierType, Double> collectModifiers(final List<? extends ItemPriceModifierProvider> itemPriceModifierProviders, final ItemPriceModificationContext itemPriceModificationContext) {
        return itemPriceModifierProviders.stream()
                .map(itemSellPriceModifierProvider -> itemSellPriceModifierProvider.calculateModification(itemPriceModificationContext))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(
                        Collectors.toMap(ItemPriceModifier::getModifierType, ItemPriceModifier::getValue, (n1, n2) -> n1 + n2)
                );
    }
}
