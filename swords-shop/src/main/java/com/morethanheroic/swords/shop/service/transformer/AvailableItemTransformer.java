package com.morethanheroic.swords.shop.service.transformer;

import com.morethanheroic.swords.shop.domain.AvailableItem;
import com.morethanheroic.swords.shop.domain.ShopDefinition;
import com.morethanheroic.swords.shop.domain.ShopItem;
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
public class AvailableItemTransformer {

    private final ItemSellPriceCalculator itemSellPriceCalculator;
    private final ItemBuyPriceCalculator itemBuyPriceCalculator;

    public List<ShopItem> transform(final UserEntity userEntity, final ShopDefinition shopDefinition) {
        return shopDefinition.getAvailableItems().stream()
                .map(availableItem -> transform(userEntity, availableItem, shopDefinition))
                .collect(Collectors.toList());
    }

    private ShopItem transform(final UserEntity userEntity, final AvailableItem availableItem, final ShopDefinition shopDefinition) {

        return ShopItem.builder()
                .amount(2000)
                .buyPrice(itemBuyPriceCalculator.calculateBuyPrice(
                        ItemPriceCalculationContext.builder()
                                .userEntity(userEntity)
                                .itemDefinition(availableItem.getItem())
                                .build()
                ))
                .sellPrice(itemSellPriceCalculator.calculateSellPrice(
                        ItemPriceCalculationContext.builder()
                                .userEntity(userEntity)
                                .itemDefinition(availableItem.getItem())
                                .build()
                ))
                .item(availableItem.getItem())
                .shopId(shopDefinition.getId())
                .build();
    }
}
