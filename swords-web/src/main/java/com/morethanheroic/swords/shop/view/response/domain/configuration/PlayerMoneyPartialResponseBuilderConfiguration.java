package com.morethanheroic.swords.shop.view.response.domain.configuration;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PlayerMoneyPartialResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final int bronze;
    private final int silver;
    private final int gold;
}
