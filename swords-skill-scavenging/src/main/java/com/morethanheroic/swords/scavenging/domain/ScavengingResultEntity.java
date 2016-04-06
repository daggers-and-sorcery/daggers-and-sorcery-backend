package com.morethanheroic.swords.scavenging.domain;

import com.morethanheroic.swords.item.domain.ItemDefinition;

public class ScavengingResultEntity {

    private final ItemDefinition item;
    private final int amount;
    private final boolean identified;

    public ScavengingResultEntity(ItemDefinition item, int amount, boolean identified) {
        this.item = item;
        this.amount = amount;
        this.identified = identified;
    }

    public ItemDefinition getItem() {
        return item;
    }

    public int getAmount() {
        return amount;
    }

    public boolean isIdentified() {
        return identified;
    }
}
