package com.morethanheroic.swords.shop.view.response.domain.buy.configuration;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.shop.domain.ShopItem;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ShopBuyListItemPartialResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final List<ShopItem> shopItems;
}
