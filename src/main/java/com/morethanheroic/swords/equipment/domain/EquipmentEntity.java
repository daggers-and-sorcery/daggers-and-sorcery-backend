package com.morethanheroic.swords.equipment.domain;

import com.morethanheroic.swords.attribute.domain.requirement.AttributeRequirementDefinition;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.equipment.repository.domain.EquipmentMapper;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.user.domain.UserEntity;

public class EquipmentEntity {

    private final UserEntity userEntity;
    private final InventoryEntity inventoryEntity;
    private final EquipmentMapper equipmentMapper;
    private final EquipmentSlotMapper equipmentSlotMapper;
    private final GlobalAttributeCalculator globalAttributeCalculator;
    private final ItemDefinitionCache itemDefinitionCache;

    public EquipmentEntity(UserEntity userEntity, InventoryEntity inventoryEntity, EquipmentMapper equipmentMapper, EquipmentSlotMapper equipmentSlotMapper, GlobalAttributeCalculator globalAttributeCalculator, ItemDefinitionCache itemDefinitionCache) {
        this.userEntity = userEntity;
        this.inventoryEntity = inventoryEntity;
        this.equipmentMapper = equipmentMapper;
        this.equipmentSlotMapper = equipmentSlotMapper;
        this.itemDefinitionCache = itemDefinitionCache;
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
                equipWithoutCheck(itemDefinitionCache.getItemDefinition(previousEquipment));
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
            if(globalAttributeCalculator.calculateActualValue(userEntity, requirement.getAttribute()).getValue() < requirement.getAmount()) {
                return false;
            }
        }

        return true;
    }

    public ItemDefinition getEquipmentDefinitionOnSlot(EquipmentSlot slot) {
        return itemDefinitionCache.getItemDefinition(getEquipmentIdOnSlot(slot));
    }

    public int getEquipmentIdOnSlot(EquipmentSlot slot) {
        switch (slot) {
            case WEAPON:
                return equipmentMapper.getEquipment(userEntity.getId()).getWeapon();
            case OFFHAND:
                return equipmentMapper.getEquipment(userEntity.getId()).getOffhand();
            case HELM:
                return equipmentMapper.getEquipment(userEntity.getId()).getHelm();
            case GLOVES:
                return equipmentMapper.getEquipment(userEntity.getId()).getGloves();
            case RING:
                return equipmentMapper.getEquipment(userEntity.getId()).getRing();
            case AMULET:
                return equipmentMapper.getEquipment(userEntity.getId()).getAmulet();
            case BOOTS:
                return equipmentMapper.getEquipment(userEntity.getId()).getBoots();
            case BRACER:
                return equipmentMapper.getEquipment(userEntity.getId()).getBracer();
            case CHEST:
                return equipmentMapper.getEquipment(userEntity.getId()).getChest();
            case LEGS:
                return equipmentMapper.getEquipment(userEntity.getId()).getLegs();
            default:
                throw new IllegalArgumentException("Wrong slot: " + slot);
        }
    }

    public boolean isEquipmentIdentifiedOnSlot(EquipmentSlot slot) {
        switch (slot) {
            case WEAPON:
                return equipmentMapper.getEquipment(userEntity.getId()).isWeaponIdentified();
            case OFFHAND:
                return equipmentMapper.getEquipment(userEntity.getId()).isOffhandIdentified();
            case HELM:
                return equipmentMapper.getEquipment(userEntity.getId()).isHelmIdentified();
            case GLOVES:
                return equipmentMapper.getEquipment(userEntity.getId()).isGlovesIdentified();
            case RING:
                return equipmentMapper.getEquipment(userEntity.getId()).isRingIdentified();
            case AMULET:
                return equipmentMapper.getEquipment(userEntity.getId()).isAmuletIdentified();
            case BOOTS:
                return equipmentMapper.getEquipment(userEntity.getId()).isBootsIdentified();
            case BRACER:
                return equipmentMapper.getEquipment(userEntity.getId()).isBracerIdentified();
            case CHEST:
                return equipmentMapper.getEquipment(userEntity.getId()).isChestIdentified();
            case LEGS:
                return equipmentMapper.getEquipment(userEntity.getId()).isLegsIdentified();
            default:
                throw new IllegalArgumentException("Wrong slot: " + slot);
        }
    }
}
