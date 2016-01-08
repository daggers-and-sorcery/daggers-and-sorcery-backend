package com.morethanheroic.swords.equipment.domain;

import com.morethanheroic.swords.attribute.service.ItemRequirementToAttributeConverter;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.equipment.cache.CacheableEquipmentProvider;
import com.morethanheroic.swords.equipment.cache.ValueCache;
import com.morethanheroic.swords.equipment.repository.dao.EquipmentDatabaseEntity;
import com.morethanheroic.swords.equipment.repository.domain.EquipmentMapper;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryFacade;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.domain.ItemRequirementDefinition;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor
public class EquipmentEntity {

    @Autowired
    private EquipmentMapper equipmentMapper;

    @Autowired
    private EquipmentSlotMapper equipmentSlotMapper;

    @Autowired
    private GlobalAttributeCalculator globalAttributeCalculator;

    @Autowired
    private ItemDefinitionCache itemDefinitionCache;

    @Autowired
    private ItemRequirementToAttributeConverter itemRequirementToAttributeConverter;

    @Autowired
    private InventoryFacade inventoryFacade;

    @Autowired
    private CacheableEquipmentProvider cacheableEquipmentProvider;

    private final UserEntity userEntity;

    private InventoryEntity inventoryEntity;
    private ValueCache<EquipmentDatabaseEntity, CacheableEquipmentProvider, Integer> equipmentProviderIntegerValueCache;

    @PostConstruct
    public void initialize() {
        inventoryEntity = inventoryFacade.getInventory(userEntity);

        equipmentProviderIntegerValueCache = new ValueCache<>(cacheableEquipmentProvider, userEntity.getId());
    }

    public boolean equipItem(ItemDefinition item, boolean identified) {
        EquipmentSlot equipmentSlot = equipmentSlotMapper.getEquipmentSlotFromItemType(item.getType());
        int previousEquipment = getEquipmentIdOnSlot(equipmentSlot);

        //Unequip the previous equipment first because the calculation should be good without it
        unequipItem(equipmentSlot);

        if (canEquip(item)) {
            equipWithoutCheck(item, identified);
            inventoryEntity.removeItem(item.getId(), 1, identified);

            return true;
        } else {
            //Reequip the item we had before if not met the requirements for the new item after removing this (the previous)
            if (previousEquipment != 0) {
                equipWithoutCheck(itemDefinitionCache.getDefinition(previousEquipment), identified);
                inventoryEntity.removeItem(previousEquipment, 1, identified);
            }
        }

        return false;
    }

    private void equipWithoutCheck(ItemDefinition item, boolean identified) {
        final EquipmentDatabaseEntity equipmentDatabaseEntity = equipmentProviderIntegerValueCache.getEntity();

        switch (equipmentSlotMapper.getEquipmentSlotFromItemType(item.getType())) {
            case WEAPON:
                equipmentDatabaseEntity.setWeapon(item.getId());
                equipmentDatabaseEntity.setWeaponIdentified(identified);

                equipmentMapper.equipWeapon(userEntity.getId(), item.getId(), identified);
                break;
            case OFFHAND:
                equipmentDatabaseEntity.setOffhand(item.getId());
                equipmentDatabaseEntity.setOffhandIdentified(identified);

                equipmentMapper.equipOffhand(userEntity.getId(), item.getId(), identified);
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    public int unequipItem(EquipmentSlot slot) {
        final EquipmentDatabaseEntity equipmentDatabaseEntity = equipmentProviderIntegerValueCache.getEntity();

        switch (slot) {
            case WEAPON:
                final int previousWeapon = equipmentDatabaseEntity.getWeapon();

                if (previousWeapon != 0) {
                    inventoryEntity.addItem(previousWeapon, 1, equipmentDatabaseEntity.isWeaponIdentified());

                    equipmentDatabaseEntity.setWeapon(0);
                    equipmentDatabaseEntity.setWeaponIdentified(true);

                    equipmentMapper.equipWeapon(userEntity.getId(), 0, true);
                }

                return previousWeapon;
            case OFFHAND:
                final int previousOffhand = equipmentDatabaseEntity.getOffhand();

                if (previousOffhand != 0) {
                    inventoryEntity.addItem(previousOffhand, 1, equipmentDatabaseEntity.isOffhandIdentified());

                    equipmentDatabaseEntity.setOffhand(0);
                    equipmentDatabaseEntity.setOffhandIdentified(true);

                    equipmentMapper.equipOffhand(userEntity.getId(), 0, true);
                }

                return previousOffhand;
            default:
                throw new IllegalArgumentException();
        }
    }

    public boolean canEquip(ItemDefinition item) {
        for (ItemRequirementDefinition requirement : item.getRequirements()) {
            if (globalAttributeCalculator.calculateActualValue(userEntity, itemRequirementToAttributeConverter.convert(requirement.getRequirement())).getValue() < requirement.getAmount()) {
                return false;
            }
        }

        return true;
    }

    public ItemDefinition getEquipmentDefinitionOnSlot(EquipmentSlot slot) {
        return itemDefinitionCache.getDefinition(getEquipmentIdOnSlot(slot));
    }

    public int getEquipmentIdOnSlot(EquipmentSlot slot) {
        final EquipmentDatabaseEntity equipmentDatabaseEntity = equipmentProviderIntegerValueCache.getEntity();

        switch (slot) {
            case WEAPON:
                return equipmentDatabaseEntity.getWeapon();
            case OFFHAND:
                return equipmentDatabaseEntity.getOffhand();
            case HELM:
                return equipmentDatabaseEntity.getHelm();
            case GLOVES:
                return equipmentDatabaseEntity.getGloves();
            case RING:
                return equipmentDatabaseEntity.getRing();
            case AMULET:
                return equipmentDatabaseEntity.getAmulet();
            case BOOTS:
                return equipmentDatabaseEntity.getBoots();
            case BRACER:
                return equipmentDatabaseEntity.getBracer();
            case CHEST:
                return equipmentDatabaseEntity.getChest();
            case LEGS:
                return equipmentDatabaseEntity.getLegs();
            default:
                throw new IllegalArgumentException("Wrong slot: " + slot);
        }
    }

    public boolean isEquipmentIdentifiedOnSlot(EquipmentSlot slot) {
        final EquipmentDatabaseEntity equipmentDatabaseEntity = equipmentProviderIntegerValueCache.getEntity();

        switch (slot) {
            case WEAPON:
                return equipmentDatabaseEntity.isWeaponIdentified();
            case OFFHAND:
                return equipmentDatabaseEntity.isOffhandIdentified();
            case HELM:
                return equipmentDatabaseEntity.isHelmIdentified();
            case GLOVES:
                return equipmentDatabaseEntity.isGlovesIdentified();
            case RING:
                return equipmentDatabaseEntity.isRingIdentified();
            case AMULET:
                return equipmentDatabaseEntity.isAmuletIdentified();
            case BOOTS:
                return equipmentDatabaseEntity.isBootsIdentified();
            case BRACER:
                return equipmentDatabaseEntity.isBracerIdentified();
            case CHEST:
                return equipmentDatabaseEntity.isChestIdentified();
            case LEGS:
                return equipmentDatabaseEntity.isLegsIdentified();
            default:
                throw new IllegalArgumentException("Wrong slot: " + slot);
        }
    }
}
