package com.morethanheroic.swords.shop.domain;

import com.morethanheroic.swords.item.domain.ItemDefinition;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Builder
public class ShopItem {

    private int shopId;
    private int amount;
    private int buyPrice;
    private int sellPrice;
    private ItemDefinition item;
}
