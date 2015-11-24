package com.morethanheroic.swords.shop.domain;

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
}
