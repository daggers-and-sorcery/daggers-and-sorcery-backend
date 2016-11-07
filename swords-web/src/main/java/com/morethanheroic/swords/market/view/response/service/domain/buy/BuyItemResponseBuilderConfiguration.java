package com.morethanheroic.swords.market.view.response.service.domain.buy;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.market.domain.BuyingResult;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BuyItemResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final UserEntity userEntity;
    private final BuyingResult buyingResult;
}
