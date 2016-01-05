package com.morethanheroic.swords.item.domain;

import com.morethanheroic.swords.money.domain.MoneyType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * Contains the definition of the item's price in a given currency.
 */
@Getter
@ToString
@Builder
public class ItemPriceDefinition {

    private MoneyType type;
    private int amount;
}
