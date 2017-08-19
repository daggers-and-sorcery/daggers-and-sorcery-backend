package com.morethanheroic.swords.equipment.view.controller;

import com.morethanheroic.swords.equipment.EquipmentEntityFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.morethanheroic.session.domain.SessionEntity;
import com.morethanheroic.swords.equipment.domain.EquipmentSlot;
import com.morethanheroic.swords.equipment.service.EquipmentResponseBuilder;
import com.morethanheroic.swords.equipment.service.EquippingService;
import com.morethanheroic.swords.inventory.domain.IdentificationType;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.inventory.service.UnidentifiedItemIdCalculator;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.service.definition.cache.ItemDefinitionCache;
import com.morethanheroic.swords.response.domain.CharacterRefreshResponse;
import com.morethanheroic.swords.user.domain.UserEntity;

import lombok.RequiredArgsConstructor;

@Lazy
@RestController
@RequiredArgsConstructor
public class EquipmentController {

    private final EquipmentEntityFactory equipmentEntityFactory;
    private final InventoryEntityFactory inventoryEntityFactory;
    private final ItemDefinitionCache itemDefinitionCache;
    private final EquipmentResponseBuilder equipmentResponseBuilder;
    private final EquippingService equippingService;

    private final UnidentifiedItemIdCalculator unidentifiedItemIdCalculator;

    @RequestMapping(value = "/equip/{itemId}", method = RequestMethod.GET)
    public CharacterRefreshResponse equip(UserEntity user, SessionEntity sessionEntity, @PathVariable int itemId) {
        final IdentificationType identifiedItem = unidentifiedItemIdCalculator.isIdentified(itemId);

        if (identifiedItem == IdentificationType.UNIDENTIFIED) {
            itemId = unidentifiedItemIdCalculator.getRealItemId(sessionEntity, itemId);
        }

        final ItemDefinition itemToEquip = itemDefinitionCache.getDefinition(itemId);
        if (inventoryEntityFactory.getEntity(user.getId()).hasItem(itemToEquip, identifiedItem) && itemToEquip.isEquipment()) {
            if (equippingService.equipItem(user, itemToEquip, identifiedItem)) {
                return equipmentResponseBuilder.build(user, EquipmentResponseBuilder.SUCCESSFULL_REQUEST);
            }
        }

        return equipmentResponseBuilder.build(user, EquipmentResponseBuilder.UNSUCCESSFULL_REQUEST);
    }


    @GetMapping("/unequip/{slotId}")
    public CharacterRefreshResponse unequip(final UserEntity user, final @PathVariable String slotId) {
        equipmentEntityFactory.getEntity(user).unequipItem(EquipmentSlot.valueOf(slotId));

        return equipmentResponseBuilder.build(user, EquipmentResponseBuilder.SUCCESSFULL_REQUEST);
    }
}
