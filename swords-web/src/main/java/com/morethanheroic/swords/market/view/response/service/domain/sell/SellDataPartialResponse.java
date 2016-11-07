package com.morethanheroic.swords.market.view.response.service.domain.sell;

import com.morethanheroic.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SellDataPartialResponse extends PartialResponse {

    private final int maximumAmount;
}
