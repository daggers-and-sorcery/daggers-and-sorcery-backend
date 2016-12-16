package com.morethanheroic.swords.shop.service.price;

import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.money.domain.MoneyType;
import com.morethanheroic.swords.shop.service.price.domain.ItemPriceCalculationContext;
import com.morethanheroic.swords.shop.service.price.modifier.ModifiedPriceCalculator;
import com.morethanheroic.swords.shop.service.price.modifier.domain.ItemPriceModificationCollector;
import com.morethanheroic.swords.shop.service.price.modifier.domain.ItemPriceModificationContext;
import com.morethanheroic.swords.shop.service.price.modifier.domain.ItemPriceModifierType;
import com.morethanheroic.swords.shop.service.price.modifier.provider.ItemBuyPriceModifierProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Calculate the buy price of an item.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ItemBuyPriceCalculator {

    private static final int MINIMUM_BUY_PRICE = 1;

    private final List<ItemBuyPriceModifierProvider> itemBuyPriceModifierProviders;
    private final ItemPriceModificationCollector itemPriceModificationCollector;
    private final ModifiedPriceCalculator modifiedPriceCalculator;

    /**
     * Calculate the buy price of an item.
     *
     * @param itemPriceCalculationContext the context of the calculation
     * @return the calculated price
     */
    public int calculateBuyPrice(final ItemPriceCalculationContext itemPriceCalculationContext) {
        final int baseBuyPrice = calculateBaseSellPrice(itemPriceCalculationContext);

        final Map<ItemPriceModifierType, Double> modifiers = itemPriceModificationCollector
                .collectModifiers(itemBuyPriceModifierProviders,
                        ItemPriceModificationContext.builder()
                                .userEntity(itemPriceCalculationContext.getUserEntity())
                                .build()
                );

        return modifiedPriceCalculator.calculateFinalPrice(baseBuyPrice, modifiers);
    }

    private int calculateBaseSellPrice(final ItemPriceCalculationContext itemPriceCalculationContext) {
        final ItemDefinition itemDefinition = itemPriceCalculationContext.getItemDefinition();

        try {
            return itemDefinition.getPriceDefinitionFor(MoneyType.MONEY).getAmount();
        } catch (IllegalArgumentException e) {
            log.error("Error happened while calculating the buy price of the item: " + itemDefinition.getName() + " with id:" + itemDefinition.getId(), e);

            return 0;
        }
    }
}
