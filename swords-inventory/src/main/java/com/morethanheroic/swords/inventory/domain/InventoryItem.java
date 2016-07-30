package com.morethanheroic.swords.inventory.domain;

import com.morethanheroic.swords.item.domain.ItemDefinition;
import lombok.Builder;
import lombok.Getter;

@Builder
public class InventoryItem {

    @Getter
    private final ItemDefinition item;

    @Getter
    private final int amount;

    private final IdentificationType identified;

    public boolean isIdentified() {
        return identified == IdentificationType.IDENTIFIED;
    }
}
