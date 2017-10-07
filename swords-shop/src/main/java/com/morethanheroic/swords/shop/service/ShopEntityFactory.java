package com.morethanheroic.swords.shop.service;

import com.morethanheroic.dependencyinjection.inject.InjectAtReturn;
import com.morethanheroic.entity.service.factory.EntityFactory;
import com.morethanheroic.swords.shop.domain.ShopDefinition;
import com.morethanheroic.swords.shop.domain.ShopEntity;
import com.morethanheroic.swords.shop.service.definition.cache.ShopDefinitionCache;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopEntityFactory implements EntityFactory<ShopEntity, Integer> {

    private final ShopDefinitionCache shopDefinitionCache;

    /**
     * @deprecated Use {@link #getEntity(ShopDefinition)} instead!
     */
    @Override
    @InjectAtReturn
    public ShopEntity getEntity(final Integer shopId) {
        return new ShopEntity(shopDefinitionCache.getDefinition(shopId));
    }


    @InjectAtReturn
    public ShopEntity getEntity(final ShopDefinition shopDefinition) {
        return new ShopEntity(shopDefinition);
    }
}
