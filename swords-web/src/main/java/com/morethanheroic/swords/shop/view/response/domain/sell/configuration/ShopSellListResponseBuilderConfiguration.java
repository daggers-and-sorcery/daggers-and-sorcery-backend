package com.morethanheroic.swords.shop.view.response.domain.sell.configuration;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.session.domain.SessionEntity;
import com.morethanheroic.swords.item.domain.ItemType;
import com.morethanheroic.swords.shop.domain.ShopDefinition;
import com.morethanheroic.swords.shop.view.response.service.sell.ShopSellItem;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Builder
@Getter
public class ShopSellListResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final UserEntity userEntity;
    private final SessionEntity sessionEntity;
    private final int bronze;
    private final int silver;
    private final int gold;
    private final ShopDefinition shopDefinition;
    private final Map<ItemType, List<ShopSellItem>> items;
}
