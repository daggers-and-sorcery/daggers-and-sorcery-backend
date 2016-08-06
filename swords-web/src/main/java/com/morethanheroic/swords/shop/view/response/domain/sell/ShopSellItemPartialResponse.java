package com.morethanheroic.swords.shop.view.response.domain.sell;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.swords.item.view.response.service.domain.response.ItemDefinitionPartialResponse;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ShopSellItemPartialResponse extends PartialResponse {

    private final int price;
    private final int amount;
    private final ItemDefinitionPartialResponse item;
}
