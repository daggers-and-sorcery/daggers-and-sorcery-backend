package com.morethanheroic.swords.market.view.service.domain.buy;

import com.morethanheroic.response.domain.PartialResponse;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ListingPartialResponse extends PartialResponse {

    private final String seller;
    private final int price;
}
