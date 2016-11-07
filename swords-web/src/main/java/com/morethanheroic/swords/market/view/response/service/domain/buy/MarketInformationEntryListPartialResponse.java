package com.morethanheroic.swords.market.view.response.service.domain.buy;

import com.morethanheroic.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class MarketInformationEntryListPartialResponse extends PartialResponse {

    private final String typeName;
    private final List<PartialResponse> offers;
}
