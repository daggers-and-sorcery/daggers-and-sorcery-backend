package com.morethanheroic.swords.shop.domain;

import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.shop.service.ShopFacade;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@Builder
public class ShopEntity {

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

    //TODO: price calculation!
    public int getShopSellPrice(ItemDefinition itemDefinition) {
        return 10;
    }

    public int getShopBuyPrice(ItemDefinition itemDefinition) {
        return 10;
    }
}
