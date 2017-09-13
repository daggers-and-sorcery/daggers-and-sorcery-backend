package com.morethanheroic.swords.equipment.domain;

import java.util.*;

import com.morethanheroic.swords.item.domain.*;
import lombok.*;

/**
 * Contains all data about an equipment slot.
 */
@Builder
public class EquipmentSlotEntity {

    @Getter
    private final EquipmentSlot slot;

    @Getter
    private final int amount;

    @Getter
    private final boolean identified;

    private final ItemDefinition item;

    public boolean isSlot(final EquipmentSlot equipmentSlot) {
        return this.slot == equipmentSlot;
    }

    public boolean hasItem() {
        return item != null;
    }

    public Optional<ItemDefinition> getItem() {
        return Optional.ofNullable(item);
    }
}
