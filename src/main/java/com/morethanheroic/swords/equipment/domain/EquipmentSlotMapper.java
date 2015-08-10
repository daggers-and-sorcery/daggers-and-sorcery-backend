package com.morethanheroic.swords.equipment.domain;

import com.morethanheroic.swords.item.domain.ItemType;
import org.springframework.stereotype.Service;

@Service
public class EquipmentSlotMapper {

    public EquipmentSlot getEquipmentSlotFromItemType(ItemType itemType) {
        switch (itemType) {
            case ONE_HANDED_SWORD:
                return EquipmentSlot.WEAPON;
            case SHIELD:
                return EquipmentSlot.OFFHAND;
            default:
                throw new IllegalArgumentException();
        }
    }
}
