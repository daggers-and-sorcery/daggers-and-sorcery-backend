package com.morethanheroic.swords.market.view.response.service.domain.buy;

import com.morethanheroic.response.domain.PartialResponse;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ListingPartialResponse extends PartialResponse {

    private final int id;
    private final String seller;
    private final int amount;
    private final int price;
}
