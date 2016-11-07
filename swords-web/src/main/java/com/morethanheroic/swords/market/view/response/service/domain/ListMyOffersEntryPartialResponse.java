package com.morethanheroic.swords.market.view.response.service.domain;

import com.morethanheroic.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ListMyOffersEntryPartialResponse extends PartialResponse {

    private final int id;
    private final String name;
    private final int amount;
    private final int price;
}
