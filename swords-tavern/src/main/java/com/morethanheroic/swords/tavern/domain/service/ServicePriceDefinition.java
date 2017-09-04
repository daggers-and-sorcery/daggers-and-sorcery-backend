package com.morethanheroic.swords.tavern.domain.service;

import com.morethanheroic.swords.money.domain.MoneyType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ServicePriceDefinition {

    private MoneyType type;
    private int amount;
}
