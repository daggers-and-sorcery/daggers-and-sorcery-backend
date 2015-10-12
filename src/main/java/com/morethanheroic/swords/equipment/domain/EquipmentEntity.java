package com.morethanheroic.swords.equipment.domain;

import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.equipment.repository.domain.EquipmentMapper;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.item.service.ItemDefinitionManager;
import com.morethanheroic.swords.item.service.loader.domain.AttributeRequirementDefinition;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;

public class EquipmentEntity {

    private final UserEntity userEntity;
    private final InventoryEntity inventoryEntity;
    private final EquipmentMapper equipmentMapper;
    private final EquipmentSlotMapper equipmentSlotMapper;
    private final GlobalAttributeCalculator globalAttributeCalculator;
    private final ItemDefinitionManager itemDefinitionManager;

    public EquipmentEntity(UserEntity userEntity, InventoryEntity inventoryEntity, EquipmentMapper equipmentMapper, EquipmentSlotMapper equipmentSlotMapper, GlobalAttributeCalculator globalAttributeCalculator, ItemDefinitionManager itemDefinitionManager) {
        this.userEntity = userEntity;
        this.inventoryEntity = inventoryEntity;
        this.equipmentMapper = equipmentMapper;
        this.equipmentSlotMapper = equipmentSlotMapper;
        this.itemDefinitionManager = itemDefinitionManager;
        this.globalAttributeCalculator = globalAttributeCalculator;
    }

    public boolean equipItem(ItemDefinition item) {
        EquipmentSlot equipmentSlot  = equipmentSlotMapper.getEquipmentSlotFromItemType(item.getType());
        int previousEquipment = getEquipmentIdOnSlot(equipmentSlot);

        unequipItem(equipmentSlot);

        if(canEquip(item)) {
            equipWithoutCheck(item);
            inventoryEntity.removeItem(item.getId(), 1);

            return true;
        } else {
            //Reequip the item we had before if not met the requirements for the new item after removing this (the previous)
            if(previousEquipment != 0) {
                equipWithoutCheck(itemDefinitionManager.getItemDefinition(previousEquipment));
                inventoryEntity.removeItem(previousEquipment, 1);
            }
        }

        return false;
    }

    private void equipWithoutCheck(ItemDefinition item) {
        switch (equipmentSlotMapper.getEquipmentSlotFromItemType(item.getType())) {
            case WEAPON:
                equipmentMapper.equipWeapon(userEntity.getId(), item.getId());
                break;
            case OFFHAND:
                equipmentMapper.equipOffhand(userEntity.getId(), item.getId());
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    public int unequipItem(EquipmentSlot slot) {
        switch (slot) {
            case WEAPON:
                int previousWeapon = equipmentMapper.getEquipment(userEntity.getId()).getWeapon();

                if (previousWeapon != 0) {
                    inventoryEntity.addItem(previousWeapon, 1);
                    equipmentMapper.equipWeapon(userEntity.getId(), 0);
                }

                return previousWeapon;
            case OFFHAND:
                int previousOffhand = equipmentMapper.getEquipment(userEntity.getId()).getOffhand();

                if (previousOffhand != 0) {
                    inventoryEntity.addItem(previousOffhand, 1);
                    equipmentMapper.equipOffhand(userEntity.getId(), 0);
                }

                return previousOffhand;
            default:
                throw new IllegalArgumentException();
        }
    }

    public boolean canEquip(ItemDefinition item) {
        for(AttributeRequirementDefinition requirement : item.getAllRequirements()) {
            if(globalAttributeCalculator.calculateActualValue(userEntity, requirement.getAttribute()) < requirement.getAmount()) {
                return false;
            }
        }

        return true;
    }

    public ItemDefinition getEquipmentDefinitionOnSlot(EquipmentSlot slot) {
        return itemDefinitionManager.getItemDefinition(getEquipmentIdOnSlot(slot));
    }

    public int getEquipmentIdOnSlot(EquipmentSlot slot) {
        switch (slot) {
            case WEAPON:
                return equipmentMapper.getEquipment(userEntity.getId()).getWeapon();
            case OFFHAND:
                return equipmentMapper.getEquipment(userEntity.getId()).getOffhand();
            case HELM:
            case GLOVES:
            case RING:
            case AMULET:
            case BOOTS:
            case BRACER:
            case CHEST:
            case LEGS:
                return 0;
            default:
                throw new IllegalArgumentException("Wrong slot: " + slot);
        }
    }
}
