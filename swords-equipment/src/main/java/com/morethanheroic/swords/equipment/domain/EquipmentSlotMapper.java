package com.morethanheroic.swords.equipment.domain;

import com.morethanheroic.swords.item.domain.ItemType;
import org.springframework.stereotype.Service;

@Service
public class EquipmentSlotMapper {

    public EquipmentSlot getEquipmentSlotFromItemType(ItemType itemType) {
        switch (itemType) {
            case TWO_HANDED_CRUSHING_WEAPONS:
            case ONE_HANDED_CRUSHING_WEAPONS:
            case TWO_HANDED_AXES:
            case ONE_HANDED_AXES:
            case THROWING_WEAPONS:
            case LONGSWORDS:
            case SHORTSWORDS:
            case POLEARMS:
            case DAGGERS:
            case LONGBOWS:
            case SHORTBOWS:
            case CROSSBOWS:
                return EquipmentSlot.WEAPON;
            case SHIELD:
                return EquipmentSlot.OFFHAND;
            case LIGHT_ARMOR:
            case HEAVY_ARMOR:
            case ROBE_ARMOR:
                return EquipmentSlot.CHEST;
            case GLOVES:
                return EquipmentSlot.GLOVES;
            case BOOTS:
                return EquipmentSlot.BOOTS;
            default:
                throw new IllegalArgumentException();
        }
    }
}
