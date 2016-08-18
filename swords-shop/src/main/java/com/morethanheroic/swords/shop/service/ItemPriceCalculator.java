package com.morethanheroic.swords.shop.service;

import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.money.domain.MoneyType;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

@Service
@Log4j
public class ItemPriceCalculator {

    private static final int MINIMUM_BUY_PRICE = 1;

    public int getSellPrice(ItemDefinition itemDefinition) {
        try {
            return Math.max(MINIMUM_BUY_PRICE, itemDefinition.getPriceDefinitionFor(MoneyType.MONEY).getAmount() / 2);
        } catch (IllegalArgumentException e) {
            log.error(e);

            return 0;
        }
    }

    public int getBuyPrice(ItemDefinition itemDefinition) {
        try {
            return itemDefinition.getPriceDefinitionFor(MoneyType.MONEY).getAmount();
        } catch (IllegalArgumentException e) {
            log.error(e);

            return 0;
        }
    }
}
