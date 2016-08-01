package com.morethanheroic.swords.shop.service;

import com.morethanheroic.entity.service.factory.EntityFactory;
import com.morethanheroic.swords.dependency.InjectAtReturn;
import com.morethanheroic.swords.shop.domain.ShopEntity;
import com.morethanheroic.swords.shop.service.cache.ShopDefinitionCache;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopEntityFactory implements EntityFactory<ShopEntity> {

    private final ShopDefinitionCache shopDefinitionCache;

    @Override
    @InjectAtReturn
    public ShopEntity getEntity(final int shopId) {
        return new ShopEntity(shopDefinitionCache.getDefinition(shopId));
    }
}
