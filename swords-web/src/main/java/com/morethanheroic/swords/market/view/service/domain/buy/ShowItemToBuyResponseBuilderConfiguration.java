package com.morethanheroic.swords.market.view.service.domain.buy;

import java.util.List;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.market.domain.MarketEntity;
import com.morethanheroic.swords.user.domain.UserEntity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ShowItemToBuyResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final UserEntity userEntity;
    private final List<MarketEntity> listings;
}
