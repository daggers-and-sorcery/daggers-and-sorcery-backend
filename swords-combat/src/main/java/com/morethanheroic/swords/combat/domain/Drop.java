package com.morethanheroic.swords.combat.domain;

import com.morethanheroic.swords.inventory.domain.IdentificationType;
import com.morethanheroic.swords.item.domain.ItemDefinition;

public class Drop {

    private final ItemDefinition item;
    private final int amount;
    private final boolean identified;

    public Drop(ItemDefinition item, int amount, boolean identified) {
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

    public IdentificationType isIdentified() {
        return identified ? IdentificationType.IDENTIFIED : IdentificationType.UNIDENTIFIED;
    }
}
