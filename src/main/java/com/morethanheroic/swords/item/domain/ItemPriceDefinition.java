package com.morethanheroic.swords.item.domain;

import com.morethanheroic.swords.money.domain.Money;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class ItemPriceDefinition {

    private Money type;
    private int amount;
}
