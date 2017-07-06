package com.morethanheroic.swords.shop.view.response.domain.configuration;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.inventory.service.pouch.domain.MoneyPouch;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PlayerMoneyPartialResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final MoneyPouch moneyPouch;
}
