package com.morethanheroic.swords.shop.view.response.domain.sell.configuration;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.session.domain.SessionEntity;
import com.morethanheroic.swords.item.domain.ItemType;
import com.morethanheroic.swords.shop.view.response.service.sell.ShopSellItem;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ShopSellTypeListPartialResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final SessionEntity sessionEntity;
    private final ItemType itemType;
    private final List<ShopSellItem> items;
}
