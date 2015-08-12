package com.morethanheroic.swords.equipment.domain;

import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.equipment.repository.domain.EquipmentMapper;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.item.service.domain.AttributeRequirementDefinition;
import com.morethanheroic.swords.item.service.domain.ItemDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;

public class EquipmentEntity {

    private final UserEntity userEntity;
    private final InventoryEntity inventoryEntity;
    private final EquipmentMapper equipmentMapper;
    private final EquipmentSlotMapper equipmentSlotMapper;
    private final GlobalAttributeCalculator globalAttributeCalculator;

    public EquipmentEntity(UserEntity userEntity, InventoryEntity inventoryEntity, EquipmentMapper equipmentMapper, EquipmentSlotMapper equipmentSlotMapper, GlobalAttributeCalculator globalAttributeCalculator) {
        this.userEntity = userEntity;
        this.inventoryEntity = inventoryEntity;
        this.equipmentMapper = equipmentMapper;
        this.equipmentSlotMapper = equipmentSlotMapper;
        this.globalAttributeCalculator = globalAttributeCalculator;
    }

    public boolean equipItem(ItemDefinition item) {
        EquipmentSlot equipmentSlot  = equipmentSlotMapper.getEquipmentSlotFromItemType(item.getType());

        int previousEquipment = unequipItem(equipmentSlot);

        switch (equipmentSlot) {
            case WEAPON:
                if(canEquip(item)) {
                    equipmentMapper.equipWeapon(userEntity.getId(), item.getId());
                    inventoryEntity.removeItem(item.getId(), 1);

                    return true;
                } else {
                    //Reequip the item we had before
                    if(previousEquipment != 0) {
                        equipmentMapper.equipWeapon(userEntity.getId(), previousEquipment);
                    }
                }
                break;
            case OFFHAND:
                if(canEquip(item)) {
                    equipmentMapper.equipOffhand(userEntity.getId(), item.getId());
                    inventoryEntity.removeItem(item.getId(), 1);

                    return true;
                } else {
                    //Reequip the item we had before
                    if(previousEquipment != 0) {
                        equipmentMapper.equipWeapon(userEntity.getId(), previousEquipment);
                    }
                }
                break;
            default:
                throw new IllegalArgumentException();
        }

        return false;
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

    public int getEquipmentOnSlot(EquipmentSlot slot) {
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
