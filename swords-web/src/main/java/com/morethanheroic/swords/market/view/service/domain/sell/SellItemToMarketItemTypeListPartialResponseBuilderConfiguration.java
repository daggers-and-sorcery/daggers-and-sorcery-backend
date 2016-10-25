package com.morethanheroic.swords.market.view.service.domain.sell;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.inventory.domain.InventoryItem;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class SellItemToMarketItemTypeListPartialResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final String itemType;
    private final List<InventoryItem> items;
}
