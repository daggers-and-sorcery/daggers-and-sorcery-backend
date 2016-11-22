package com.morethanheroic.swords.market.domain;

import com.morethanheroic.swords.item.domain.ItemDefinition;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MarketOfferInformationEntry {

    private final ItemDefinition item;
    private final int amount;
    private final int lowestPrice;
}
