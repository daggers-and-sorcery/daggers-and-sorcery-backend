package com.morethanheroic.swords.inventory.service.pouch.domain;

import com.morethanheroic.swords.money.domain.MoneyType;
import lombok.Builder;

/**
 * Provide a way to easily calculate types (like silver, gold etc) of a currency.
 */
@Builder
public class MoneyPouch {

    private final MoneyType type;
    private final int amount;

    public int getBronze() {
        return amount % 100;
    }

    public int getSilver() {
        return (amount / 100) % 100;
    }

    public int getGold() {
        return amount / 10000;
    }
}
