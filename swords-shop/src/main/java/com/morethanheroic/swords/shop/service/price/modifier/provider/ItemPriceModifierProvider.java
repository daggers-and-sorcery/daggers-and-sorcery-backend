package com.morethanheroic.swords.shop.service.price.modifier.provider;

import com.morethanheroic.swords.shop.service.price.modifier.domain.ItemPriceModificationContext;
import com.morethanheroic.swords.shop.service.price.modifier.domain.ItemPriceModifier;

import java.util.Optional;

/**
 * Provides a way to modify the price of an item. Don't implement this class directly. Implement either
 * {@link ItemSellPriceModifierProvider} or {@link ItemBuyPriceModifierProvider}.
 */
public interface ItemPriceModifierProvider {

    /**
     * Provides a modification on the price of an item.
     *
     * @param itemPriceModificationContext the context of the modification
     * @return the modification, empty if no modification should be made
     */
    Optional<ItemPriceModifier> calculateModification(final ItemPriceModificationContext itemPriceModificationContext);
}
