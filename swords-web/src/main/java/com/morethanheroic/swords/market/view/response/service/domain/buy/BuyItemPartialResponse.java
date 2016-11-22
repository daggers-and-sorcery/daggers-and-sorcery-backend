package com.morethanheroic.swords.market.view.response.service.domain.buy;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.swords.market.domain.BuyingResult;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BuyItemPartialResponse extends PartialResponse {

    private final BuyingResult result;
}
