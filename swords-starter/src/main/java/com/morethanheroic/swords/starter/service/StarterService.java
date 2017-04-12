package com.morethanheroic.swords.starter.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.morethanheroic.swords.equipment.service.EquippingService;
import com.morethanheroic.swords.inventory.domain.IdentificationType;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.starter.service.domain.StarterArmorSet;
import com.morethanheroic.swords.starter.service.domain.StartingEquipmentAwardingContext;
import com.morethanheroic.swords.starter.service.domain.StartingItem;
import com.morethanheroic.swords.starter.service.domain.StartingWeaponSet;
import com.morethanheroic.swords.user.domain.UserEntity;

import lombok.RequiredArgsConstructor;

/**
 * Awards the starter equipment to a user.
 */
@Service
@RequiredArgsConstructor
public class StarterService {

    private final InventoryEntityFactory inventoryEntityFactory;
    private final StarterWeaponCalculator starterWeaponCalculator;
    private final StarterArmorCalculator starterArmorCalculator;
    private final StarterItemCalculator starterItemCalculator;
    private final EquippingService equippingService;

    /**
     * Awards the starting items to the player.
     * 
     * @param startingEquipmentAwardingContext the context of the starting equipment
     */
    public void awardStartingEquipment(final StartingEquipmentAwardingContext startingEquipmentAwardingContext) {
        awardEquipment(startingEquipmentAwardingContext);
        awardItems(startingEquipmentAwardingContext);
    }

    private void awardEquipment(final StartingEquipmentAwardingContext startingEquipmentAwardingContext) {
        awardWeapon(startingEquipmentAwardingContext);
        awardArmor(startingEquipmentAwardingContext);
    }

    private void awardWeapon(final StartingEquipmentAwardingContext startingEquipmentAwardingContext) {
        final UserEntity userEntity = startingEquipmentAwardingContext.getUserEntity();
        final StartingWeaponSet startingWeaponSet = starterWeaponCalculator.getStarterWeapon(startingEquipmentAwardingContext.getStartingWeapon());

        equippingService.equipItem(userEntity, startingWeaponSet.getWeapon(), IdentificationType.IDENTIFIED);

        if (startingWeaponSet.getQuiver() != null) {
            //Need to award the ammunition into the inventory first because of the equipping logic
            inventoryEntityFactory.getEntity(userEntity).addItem(startingWeaponSet.getQuiver().getAmmunition(), startingWeaponSet.getQuiver().getAmount());

            equippingService.equipItem(userEntity, startingWeaponSet.getQuiver().getAmmunition(), IdentificationType.IDENTIFIED);
        }
    }

    private void awardArmor(final StartingEquipmentAwardingContext startingEquipmentAwardingContext) {
        final UserEntity userEntity = startingEquipmentAwardingContext.getUserEntity();
        final StarterArmorSet starterArmorSet = starterArmorCalculator.getStarterArmor(startingEquipmentAwardingContext.getStartingArmor());

        equippingService.equipItem(userEntity, starterArmorSet.getArmor(), IdentificationType.IDENTIFIED);
        equippingService.equipItem(userEntity, starterArmorSet.getBoots(), IdentificationType.IDENTIFIED);
        equippingService.equipItem(userEntity, starterArmorSet.getOffhand(), IdentificationType.IDENTIFIED);
    }

    private void awardItems(final StartingEquipmentAwardingContext startingEquipmentAwardingContext) {
        final InventoryEntity inventoryEntity = inventoryEntityFactory.getEntity(startingEquipmentAwardingContext.getUserEntity());

        starterItemCalculator.getStartingItems(startingEquipmentAwardingContext.getStartingArmor())
                     .forEach(startingItem -> inventoryEntity.addItem(startingItem.getItemDefinition(), startingItem.getItemAmount()));
    }
}
