package com.morethanheroic.swords.combat.domain;

import com.morethanheroic.swords.item.domain.ItemDefinition;

public class Drop {

    private final ItemDefinition item;
    private final int amount;

    public Drop(ItemDefinition item, int amount) {
        this.item = item;
        this.amount = amount;
    }

    public ItemDefinition getItem() {
        return item;
    }

    public int getAmount() {
        return amount;
    }
}
