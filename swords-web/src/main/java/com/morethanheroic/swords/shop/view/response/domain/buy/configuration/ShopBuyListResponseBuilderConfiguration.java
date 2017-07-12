package com.morethanheroic.swords.shop.view.response.domain.buy.configuration;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.inventory.service.pouch.domain.MoneyPouch;
import com.morethanheroic.swords.item.domain.ItemType;
import com.morethanheroic.swords.shop.domain.ShopDefinition;
import com.morethanheroic.swords.shop.domain.ShopItem;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Builder
@Getter
public class ShopBuyListResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final UserEntity userEntity;
    private final MoneyPouch moneyPouch;
    private final ShopDefinition shopDefinition;
    private final Map<ItemType, List<ShopItem>> items;
}
