package com.morethanheroic.swords.shop.service.transformer;

import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.shop.domain.ShopItem;
import com.morethanheroic.swords.shop.repository.dao.ShopItemDatabaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShopItemTransformer {

    @Autowired
    private ItemDefinitionCache itemDefinitionCache;

    public List<ShopItem> transform(List<ShopItemDatabaseEntity> shopItemDatabaseEntityList) {
        return shopItemDatabaseEntityList.stream().map(this::transform).collect(Collectors.toList());
    }

    public ShopItem transform(ShopItemDatabaseEntity shopItemDatabaseEntity) {
        return ShopItem.builder()
                .itemAmount(shopItemDatabaseEntity.getItemAmount())
                .item(itemDefinitionCache.getDefinition(shopItemDatabaseEntity.getItemId()))
                .shopId(shopItemDatabaseEntity.getShopId())
                .build();
    }
}
