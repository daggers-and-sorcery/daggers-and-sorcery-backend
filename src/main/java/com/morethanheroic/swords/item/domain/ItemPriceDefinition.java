package com.morethanheroic.swords.item.domain;

import com.morethanheroic.swords.money.domain.MoneyType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class ItemPriceDefinition {

    private MoneyType type;
    private int amount;
}
