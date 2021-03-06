package com.morethanheroic.swords.market.view.response.service.domain.sell;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.swords.item.view.response.service.domain.response.ItemDefinitionPartialResponse;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SellItemToMaketItemPartialResponse extends PartialResponse {

    private final int amount;
    private final ItemDefinitionPartialResponse definition;
}
