package com.morethanheroic.swords.combat.domain;

import com.morethanheroic.swords.item.domain.ItemDefinition;

public class ScavengingEntity {

    private final ItemDefinition item;
    private final int amount;
    private final boolean identified;

    public ScavengingEntity(ItemDefinition item, int amount, boolean identified) {
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
