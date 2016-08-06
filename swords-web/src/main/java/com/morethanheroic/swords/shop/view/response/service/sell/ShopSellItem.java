package com.morethanheroic.swords.shop.view.response.service.sell;

import com.morethanheroic.swords.inventory.domain.InventoryItem;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ShopSellItem {

    private final InventoryItem inventoryItem;
    private final int sellPrice;
}
