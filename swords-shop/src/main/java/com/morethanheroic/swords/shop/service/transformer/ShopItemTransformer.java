package com.morethanheroic.swords.shop.service.transformer;

import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.shop.domain.ShopItem;
import com.morethanheroic.swords.shop.repository.dao.ShopItemDatabaseEntity;
import com.morethanheroic.swords.shop.service.ItemPriceCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopItemTransformer {

    private final ItemDefinitionCache itemDefinitionCache;
    private final ItemPriceCalculator itemPriceCalculator;

    public List<ShopItem> transform(List<ShopItemDatabaseEntity> shopItemDatabaseEntityList) {
        return shopItemDatabaseEntityList.stream().map(this::transform).collect(Collectors.toList());
    }

    public ShopItem transform(ShopItemDatabaseEntity shopItemDatabaseEntity) {
        final ItemDefinition itemDefinition = itemDefinitionCache.getDefinition(shopItemDatabaseEntity.getItemId());

        return ShopItem.builder()
                .amount(shopItemDatabaseEntity.getItemAmount())
                .buyPrice(itemPriceCalculator.getBuyPrice(itemDefinition))
                .sellPrice(itemPriceCalculator.getSellPrice(itemDefinition))
                .item(itemDefinitionCache.getDefinition(shopItemDatabaseEntity.getItemId()))
                .shopId(shopItemDatabaseEntity.getShopId())
                .build();
    }
}
