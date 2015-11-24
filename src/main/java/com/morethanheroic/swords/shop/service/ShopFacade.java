package com.morethanheroic.swords.shop.service;

import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.shop.domain.ShopDefinition;
import com.morethanheroic.swords.shop.domain.ShopEntity;
import com.morethanheroic.swords.shop.domain.ShopItem;
import com.morethanheroic.swords.shop.repository.domain.ShopMapper;
import com.morethanheroic.swords.shop.service.cache.ShopDefinitionCache;
import com.morethanheroic.swords.shop.service.transformer.ShopItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopFacade {

    @Autowired
    private ShopDefinitionCache shopDefinitionCache;

    @Autowired
    private ShopItemTransformer shopItemTransformer;

    @Autowired
    private ShopMapper shopMapper;

    public ShopEntity getShopEntity(int shopId) {
        ShopDefinition shopDefinition = shopDefinitionCache.getShopDefinition(shopId);

        return ShopEntity.builder()
                .id(shopDefinition.getId())
                .shopDefinition(shopDefinition)
                .shopFacade(this)
                .build();
    }

    public boolean isShopExists(int shopId) {
        return shopDefinitionCache.isShopExists(shopId);
    }

    public List<ShopItem> getItemsInShop(ShopDefinition shopDefinition) {
        return shopItemTransformer.transform(shopMapper.getItemsInShop(shopDefinition.getId()));
    }

    //TODO: implement these
    public void addItemToShop(ShopDefinition shopDefinition, ItemDefinition itemDefinition, int amount) {

    }

    public void removeItemFromShop(ShopDefinition shopDefinition, ItemDefinition itemDefinition, int amount) {

    }

    public boolean shopHasItem(ShopDefinition shopDefinition, ItemDefinition itemDefinition, int amount) {
        return true;
    }
}
