package com.morethanheroic.swords.market.view.service.domain.buy;

import com.morethanheroic.response.domain.PartialResponse;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ListedItemsPartialResponse extends PartialResponse {

    private final PartialResponse item;
    private final int amount;
    private final int cheapestPrice;
}
