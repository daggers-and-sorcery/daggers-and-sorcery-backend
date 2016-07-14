package com.morethanheroic.swords.shop.domain;

import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.money.domain.MoneyType;
import com.morethanheroic.swords.shop.service.ShopFacade;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@Builder
public class ShopEntity {

    private static final int MINIMUM_BUY_PRICE = 1;

    private final int id;
    private final ShopDefinition shopDefinition;
    private final ShopFacade shopFacade;

    public List<ShopItem> getAllItemsInShop() {
        return shopFacade.getItemsInShop(shopDefinition);
    }

    public void buyItem(ItemDefinition itemDefinition, int amount) {
        shopFacade.addItemToShop(shopDefinition, itemDefinition, amount);
    }

    public void sellItem(ItemDefinition itemDefinition, int amount) {
        shopFacade.removeItemFromShop(shopDefinition, itemDefinition, amount);
    }

    public boolean hasItem(ItemDefinition itemDefinition, int amount) {
        return shopFacade.shopHasItem(shopDefinition, itemDefinition, amount);
    }

    public int getShopSellPrice(ItemDefinition itemDefinition) {
        return itemDefinition.getPriceDefinitionFor(MoneyType.MONEY).getAmount();
    }

    public int getShopBuyPrice(ItemDefinition itemDefinition) {
        return Math.max(MINIMUM_BUY_PRICE, itemDefinition.getPriceDefinitionFor(MoneyType.MONEY).getAmount() / 2);
    }
}
