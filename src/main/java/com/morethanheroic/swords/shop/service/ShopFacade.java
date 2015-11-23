package com.morethanheroic.swords.shop.service;

import com.morethanheroic.swords.shop.service.cache.ShopDefinitionCache;
import com.morethanheroic.swords.shop.service.domain.ShopDefinition;
import com.morethanheroic.swords.shop.service.domain.ShopEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopFacade {

    @Autowired
    private ShopDefinitionCache shopDefinitionCache;

    public ShopEntity getShopEntity(int shopId) {
        ShopDefinition shopDefinition = shopDefinitionCache.getShopDefinition(shopId);

        return ShopEntity.builder()
                .id(shopDefinition.getId())
                .shopDefinition(shopDefinition)
                .build();
    }

    public boolean isShopExists(int shopId) {
        return shopDefinitionCache.isShopExists(shopId);
    }
}
