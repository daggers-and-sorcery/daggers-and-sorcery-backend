package com.morethanheroic.swords.market.view.service.domain.sell;

import com.morethanheroic.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class SellItemToMarketItemTypeListPartialResponse extends PartialResponse {

    private final String typeName;
    private final List<PartialResponse> items;
}
