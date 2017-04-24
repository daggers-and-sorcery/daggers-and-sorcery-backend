package com.morethanheroic.swords.shop.service.price;

import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.money.domain.MoneyType;
import com.morethanheroic.swords.shop.service.price.domain.ItemPriceCalculationContext;
import com.morethanheroic.swords.shop.service.price.modifier.provider.ItemSellPriceModifierProvider;
import com.morethanheroic.swords.shop.service.price.modifier.ModifiedPriceCalculator;
import com.morethanheroic.swords.shop.service.price.modifier.domain.ItemPriceModificationCollector;
import com.morethanheroic.swords.shop.service.price.modifier.domain.ItemPriceModificationContext;
import com.morethanheroic.swords.shop.service.price.modifier.domain.ItemPriceModifierType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Calculate the sell price of an item.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ItemSellPriceCalculator {

    private static final int MINIMUM_SELL_PRICE = 1;

    private final List<ItemSellPriceModifierProvider> itemSellPriceModifierProviders;
    private final ItemPriceModificationCollector itemPriceModificationCollector;
    private final ModifiedPriceCalculator modifiedPriceCalculator;

    /**
     * Calculate the sell price of an item.
     *
     * @param itemPriceCalculationContext the context of the calculation
     * @return the calculated price
     */
    public int calculateSellPrice(final ItemPriceCalculationContext itemPriceCalculationContext) {
        final int baseSellPrice = calculateBaseSellPrice(itemPriceCalculationContext);

        final Map<ItemPriceModifierType, Double> modifiers = itemPriceModificationCollector
                .collectModifiers(itemSellPriceModifierProviders,
                        ItemPriceModificationContext.builder()
                                .userEntity(itemPriceCalculationContext.getUserEntity())
                                .build()
                );

        return modifiedPriceCalculator.calculateFinalPrice(baseSellPrice, modifiers);
    }

    private int calculateBaseSellPrice(final ItemPriceCalculationContext itemPriceCalculationContext) {
        final ItemDefinition itemDefinition = itemPriceCalculationContext.getItemDefinition();

        try {
            return Math.max(MINIMUM_SELL_PRICE, itemDefinition.getPriceDefinitionFor(MoneyType.MONEY).getAmount() / 2);
        } catch (IllegalArgumentException e) {
            log.error("Error happened while calculating the sell price of the item: " + itemDefinition.getName() + " with id:" + itemDefinition.getId(), e);

            return 0;
        }
    }
}
