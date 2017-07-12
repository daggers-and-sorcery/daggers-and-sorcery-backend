package com.morethanheroic.swords.shop.service.transformer;

import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.shop.domain.ShopItem;
import com.morethanheroic.swords.shop.repository.dao.ShopItemDatabaseEntity;
import com.morethanheroic.swords.shop.service.price.ItemBuyPriceCalculator;
import com.morethanheroic.swords.shop.service.price.ItemSellPriceCalculator;
import com.morethanheroic.swords.shop.service.price.domain.ItemPriceCalculationContext;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopItemTransformer {

    private final ItemDefinitionCache itemDefinitionCache;
    private final ItemSellPriceCalculator itemSellPriceCalculator;
    private final ItemBuyPriceCalculator itemBuyPriceCalculator;

    public List<ShopItem> transform(final UserEntity userEntity, final List<ShopItemDatabaseEntity> shopItemDatabaseEntityList) {
        return shopItemDatabaseEntityList.stream()
                .map(shopItemDatabaseEntity -> transform(userEntity, shopItemDatabaseEntity))
                .collect(Collectors.toList());
    }

    public ShopItem transform(final UserEntity userEntity, final ShopItemDatabaseEntity shopItemDatabaseEntity) {
        final ItemDefinition itemDefinition = itemDefinitionCache.getDefinition(shopItemDatabaseEntity.getItemId());

        return ShopItem.builder()
                .amount(shopItemDatabaseEntity.getItemAmount())
                .buyPrice(itemBuyPriceCalculator.calculateBuyPrice(
                        ItemPriceCalculationContext.builder()
                                .userEntity(userEntity)
                                .itemDefinition(itemDefinition)
                                .build()
                ))
                .sellPrice(itemSellPriceCalculator.calculateSellPrice(
                        ItemPriceCalculationContext.builder()
                                .userEntity(userEntity)
                                .itemDefinition(itemDefinition)
                                .build()
                ))
                .item(itemDefinition)
                .shopId(shopItemDatabaseEntity.getShopId())
                .build();
    }
}
