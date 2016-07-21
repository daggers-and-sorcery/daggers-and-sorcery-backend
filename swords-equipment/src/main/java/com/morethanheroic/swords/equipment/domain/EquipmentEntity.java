package com.morethanheroic.swords.equipment.domain;

import com.morethanheroic.swords.attribute.service.ItemRequirementToAttributeConverter;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.cache.value.ValueCache;
import com.morethanheroic.swords.equipment.repository.dao.EquipmentDatabaseEntity;
import com.morethanheroic.swords.equipment.repository.domain.EquipmentMapper;
import com.morethanheroic.swords.equipment.service.EquipmentValueCacheProvider;
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
    private EquipmentValueCacheProvider cacheableEquipmentProvider;

    private final UserEntity userEntity;

    private InventoryEntity inventoryEntity;
    private ValueCache<EquipmentDatabaseEntity, EquipmentValueCacheProvider, Integer> equipmentProviderIntegerValueCache;

    @PostConstruct
    public void initialize() {
        inventoryEntity = inventoryFacade.getInventory(userEntity);

        equipmentProviderIntegerValueCache = new ValueCache<>(cacheableEquipmentProvider, userEntity.getId());
    }

    public boolean equipItem(ItemDefinition item, boolean identified) {
        final EquipmentSlot equipmentSlot = equipmentSlotMapper.getEquipmentSlotFromItemType(item.getType());

        if (equipmentSlot == EquipmentSlot.OFFHAND) {
            final ItemDefinition weapon = itemDefinitionCache.getDefinition(getEquipmentIdOnSlot(EquipmentSlot.WEAPON));

            if (getEquipmentIdOnSlot(EquipmentSlot.WEAPON) != 0 && equipmentSlotMapper.isTwoHandedWeapon(weapon.getType())) {
                //TODO: unequip the weapon and equip the offhand item
                return false;
            }
        }

        final int previousEquipment = getEquipmentIdOnSlot(equipmentSlot);
        final int previousOffhandEquipment = getEquipmentIdOnSlot(EquipmentSlot.OFFHAND);

        //Unequip the previous equipment first because the calculation should be good without it
        unequipItem(equipmentSlot);
        if (equipmentSlotMapper.isTwoHandedWeapon(item.getType())) {
            unequipItem(EquipmentSlot.OFFHAND);
        }

        if (canEquip(item)) {
            equipWithoutCheck(item, identified);
            inventoryEntity.removeItem(item.getId(), 1, identified);

            return true;
        } else {
            //Reequip the item we had before if not met the requirements for the new item after removing this (the previous)
            if (previousEquipment != 0) {
                equipWithoutCheck(itemDefinitionCache.getDefinition(previousEquipment), identified);
                inventoryEntity.removeItem(previousEquipment, 1, identified);

                if (previousOffhandEquipment != 0) {
                    equipWithoutCheck(itemDefinitionCache.getDefinition(previousOffhandEquipment), identified);
                    inventoryEntity.removeItem(previousOffhandEquipment, 1, identified);
                }
            }
        }

        return false;
    }

    private void equipWithoutCheck(ItemDefinition item, boolean identified) {
        final EquipmentDatabaseEntity equipmentDatabaseEntity = equipmentProviderIntegerValueCache.getEntity();
        final EquipmentSlot slot = equipmentSlotMapper.getEquipmentSlotFromItemType(item.getType());

        switch (slot) {
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
            case GLOVES:
                equipmentDatabaseEntity.setGloves(item.getId());
                equipmentDatabaseEntity.setGlovesIdentified(identified);

                equipmentMapper.equipGloves(userEntity.getId(), item.getId(), identified);
                break;
            case BOOTS:
                equipmentDatabaseEntity.setBoots(item.getId());
                equipmentDatabaseEntity.setBootsIdentified(identified);

                equipmentMapper.equipBoots(userEntity.getId(), item.getId(), identified);
                break;
            case AMULET:
                equipmentDatabaseEntity.setAmulet(item.getId());
                equipmentDatabaseEntity.setAmuletIdentified(identified);

                equipmentMapper.equipAmulet(userEntity.getId(), item.getId(), identified);
                break;
            case CHEST:
                equipmentDatabaseEntity.setChest(item.getId());
                equipmentDatabaseEntity.setChestIdentified(identified);

                equipmentMapper.equipChest(userEntity.getId(), item.getId(), identified);
                break;
            case LEGS:
                equipmentDatabaseEntity.setLegs(item.getId());
                equipmentDatabaseEntity.setLegsIdentified(identified);

                equipmentMapper.equipLegs(userEntity.getId(), item.getId(), identified);
                break;
            default:
                throw new IllegalArgumentException("Slot: " + slot + " is not supported at equipping.");
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
            case GLOVES:
                final int previousGloves = equipmentDatabaseEntity.getGloves();

                if (previousGloves != 0) {
                    inventoryEntity.addItem(previousGloves, 1, equipmentDatabaseEntity.isGlovesIdentified());

                    equipmentDatabaseEntity.setGloves(0);
                    equipmentDatabaseEntity.setGlovesIdentified(true);

                    equipmentMapper.equipGloves(userEntity.getId(), 0, true);
                }

                return previousGloves;
            case BOOTS:
                final int previousBoots = equipmentDatabaseEntity.getBoots();

                if (previousBoots != 0) {
                    inventoryEntity.addItem(previousBoots, 1, equipmentDatabaseEntity.isBootsIdentified());

                    equipmentDatabaseEntity.setBoots(0);
                    equipmentDatabaseEntity.setBootsIdentified(true);

                    equipmentMapper.equipBoots(userEntity.getId(), 0, true);
                }

                return previousBoots;
            case AMULET:
                final int previousAmulet = equipmentDatabaseEntity.getAmulet();

                if (previousAmulet != 0) {
                    inventoryEntity.addItem(previousAmulet, 1, equipmentDatabaseEntity.isAmuletIdentified());

                    equipmentDatabaseEntity.setAmulet(0);
                    equipmentDatabaseEntity.setAmuletIdentified(true);

                    equipmentMapper.equipAmulet(userEntity.getId(), 0, true);
                }

                return previousAmulet;
            case CHEST:
                final int previousChest = equipmentDatabaseEntity.getChest();

                if (previousChest != 0) {
                    inventoryEntity.addItem(previousChest, 1, equipmentDatabaseEntity.isChestIdentified());

                    equipmentDatabaseEntity.setChest(0);
                    equipmentDatabaseEntity.setChestIdentified(true);

                    equipmentMapper.equipChest(userEntity.getId(), 0, true);
                }

                return previousChest;
            case LEGS:
                final int previousLegs = equipmentDatabaseEntity.getLegs();

                if (previousLegs != 0) {
                    inventoryEntity.addItem(previousLegs, 1, equipmentDatabaseEntity.isLegsIdentified());

                    equipmentDatabaseEntity.setLegs(0);
                    equipmentDatabaseEntity.setLegsIdentified(true);

                    equipmentMapper.equipLegs(userEntity.getId(), 0, true);
                }

                return previousLegs;
            default:
                throw new IllegalArgumentException("Slot: " + slot + " is not supported at unequipping.");
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
            case LEFT_RING:
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
            case LEFT_RING:
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
