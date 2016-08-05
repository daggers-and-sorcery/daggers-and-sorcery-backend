package com.morethanheroic.swords.shop.service;

import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.money.domain.MoneyType;
import org.springframework.stereotype.Service;

@Service
public class ItemPriceCalculator {

    private static final int MINIMUM_BUY_PRICE = 1;

    public int getSellPrice(ItemDefinition itemDefinition) {
        return itemDefinition.getPriceDefinitionFor(MoneyType.MONEY).getAmount();
    }

    public int getBuyPrice(ItemDefinition itemDefinition) {
        return Math.max(MINIMUM_BUY_PRICE, itemDefinition.getPriceDefinitionFor(MoneyType.MONEY).getAmount() / 2);
    }
}
