package com.morethanheroic.swords.market.view.response.service.domain.sell;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.swords.market.domain.SellingResult;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SellItemPartialResponse extends PartialResponse {

    private final SellingResult sellingResult;
}
