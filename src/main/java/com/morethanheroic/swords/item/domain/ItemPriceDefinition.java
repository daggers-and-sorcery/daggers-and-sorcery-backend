package com.morethanheroic.swords.item.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class ItemPriceDefinition {

    private Price type;
    private int amount;
}
