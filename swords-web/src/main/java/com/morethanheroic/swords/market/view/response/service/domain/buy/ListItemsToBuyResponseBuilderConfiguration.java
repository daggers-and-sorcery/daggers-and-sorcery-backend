package com.morethanheroic.swords.market.view.response.service.domain.buy;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.item.domain.ItemType;
import com.morethanheroic.swords.market.domain.MarketOfferInformationEntry;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@Builder
public class ListItemsToBuyResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final UserEntity userEntity;
    private final Map<ItemType, List<MarketOfferInformationEntry>> offers;
}
