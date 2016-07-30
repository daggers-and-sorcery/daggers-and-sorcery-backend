package com.morethanheroic.swords.equipment.service;

import com.morethanheroic.swords.equipment.domain.EquipmentSlot;
import com.morethanheroic.swords.item.domain.ItemType;
import org.springframework.stereotype.Service;

@Service
public class EquipmentSlotMapper {

    public EquipmentSlot getEquipmentSlotFromItemType(final ItemType itemType) {
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
            case STAFF:
            case WAND:
                return EquipmentSlot.WEAPON;
            case SHIELD:
            case FOCUS:
                return EquipmentSlot.OFFHAND;
            case LIGHT_ARMOR:
            case HEAVY_ARMOR:
            case ROBE_ARMOR:
                return EquipmentSlot.CHEST;
            case GLOVES:
                return EquipmentSlot.GLOVES;
            case BOOTS:
                return EquipmentSlot.BOOTS;
            case AMULET:
                return EquipmentSlot.AMULET;
            case PLATELEGS:
                return EquipmentSlot.LEGS;
            case ARROW:
                return EquipmentSlot.QUIVER;
            default:
                throw new IllegalArgumentException("Unsupported item type: " + itemType);
        }
    }

    public boolean isTwoHandedWeapon(final ItemType itemType) {
        if (getEquipmentSlotFromItemType(itemType) != EquipmentSlot.WEAPON) {
            return false;
        }

        switch (itemType) {
            case TWO_HANDED_CRUSHING_WEAPONS:
            case TWO_HANDED_AXES:
            case POLEARMS:
            case LONGBOWS:
            case SHORTBOWS:
            case CROSSBOWS:
            case STAFF:
                return true;
            case ONE_HANDED_CRUSHING_WEAPONS:
            case ONE_HANDED_AXES:
            case THROWING_WEAPONS:
            case LONGSWORDS:
            case SHORTSWORDS:
            case DAGGERS:
            case WAND:
                return false;
            default:
                throw new IllegalArgumentException();
        }
    }
}
