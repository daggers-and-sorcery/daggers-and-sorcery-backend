package com.morethanheroic.swords.equipment.service;

import com.morethanheroic.swords.equipment.EquipmentEntityFactory;
import com.morethanheroic.swords.equipment.domain.EquipmentEntity;
import com.morethanheroic.swords.equipment.domain.EquipmentSlot;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.service.definition.cache.ItemDefinitionCache;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.inventory.domain.IdentificationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EquippingService {

    private final EquipmentSlotMapper equipmentSlotMapper;
    private final ItemDefinitionCache itemDefinitionCache;
    private final EquipmentEntityFactory equipmentFacade;
    private final InventoryEntityFactory inventoryEntityFactory;

    public boolean equipItem(final UserEntity userEntity, final ItemDefinition item, final IdentificationType identification) {
        final EquipmentEntity equipmentEntity = equipmentFacade.getEntity(userEntity);
        final InventoryEntity inventoryEntity = inventoryEntityFactory.getEntity(userEntity);

        final EquipmentSlot equipmentSlot = equipmentSlotMapper.getEquipmentSlotFromItemType(item.getSubtype());

        if (equipmentSlot == EquipmentSlot.OFFHAND) {
            final ItemDefinition weapon = itemDefinitionCache.getDefinition(equipmentEntity.getEquipmentIdOnSlot(EquipmentSlot.WEAPON));

            if (equipmentEntity.getEquipmentIdOnSlot(EquipmentSlot.WEAPON) != 0 && equipmentSlotMapper.isTwoHandedWeapon(weapon.getSubtype())) {
                //TODO: unequip the weapon and equip the offhand item
                return false;
            }
        }

        final int previousEquipment = equipmentEntity.getEquipmentIdOnSlot(equipmentSlot);
        final int previousOffhandEquipment = equipmentEntity.getEquipmentIdOnSlot(EquipmentSlot.OFFHAND);

        //TODO: move the unequip too
        //Unequip the previous equipment first because the calculation should be good without it
        equipmentEntity.unequipItem(equipmentSlot);
        if (equipmentSlotMapper.isTwoHandedWeapon(item.getSubtype())) {
            equipmentEntity.unequipItem(EquipmentSlot.OFFHAND);
        }

        if (equipmentEntity.canEquip(item)) {
            equipmentEntity.equipWithoutCheck(item, identification == IdentificationType.IDENTIFIED);

            if (equipmentSlot != EquipmentSlot.QUIVER) {
                inventoryEntity.removeItem(item, 1, identification);
            } else {
                inventoryEntity.removeItem(item, inventoryEntity.getItemAmount(item, identification), identification);
            }

            return true;
        } else {
            //Reequip the item we had before if not met the requirements for the new item after removing this (the previous)
            if (previousEquipment != 0) {
                equipmentEntity.equipWithoutCheck(itemDefinitionCache.getDefinition(previousEquipment), identification == IdentificationType.IDENTIFIED);
                inventoryEntity.removeItem(previousEquipment, 1, identification == IdentificationType.IDENTIFIED);

                if (previousOffhandEquipment != 0) {
                    equipmentEntity.equipWithoutCheck(itemDefinitionCache.getDefinition(previousOffhandEquipment), identification == IdentificationType.IDENTIFIED);
                    inventoryEntity.removeItem(previousOffhandEquipment, 1, identification == IdentificationType.IDENTIFIED);
                }
            }
        }

        return false;
    }
}
