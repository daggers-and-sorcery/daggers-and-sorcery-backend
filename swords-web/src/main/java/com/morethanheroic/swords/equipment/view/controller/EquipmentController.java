package com.morethanheroic.swords.equipment.view.controller;

import com.morethanheroic.session.domain.SessionEntity;
import com.morethanheroic.swords.equipment.domain.EquipmentSlot;
import com.morethanheroic.swords.equipment.service.EquipmentFacade;
import com.morethanheroic.swords.equipment.service.EquipmentResponseBuilder;
import com.morethanheroic.swords.inventory.domain.IdentificationType;
import com.morethanheroic.swords.inventory.service.InventoryFacade;
import com.morethanheroic.swords.inventory.service.UnidentifiedItemIdCalculator;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.response.domain.CharacterRefreshResponse;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Lazy
@RestController
@RequiredArgsConstructor
public class EquipmentController {

    private final EquipmentFacade equipmentFacade;
    private final InventoryFacade inventoryFacade;
    private final ItemDefinitionCache itemDefinitionCache;
    private final EquipmentResponseBuilder equipmentResponseBuilder;
    private final UnidentifiedItemIdCalculator unidentifiedItemIdCalculator;

    @RequestMapping(value = "/equip/{itemId}", method = RequestMethod.GET)
    public CharacterRefreshResponse equip(UserEntity user, SessionEntity sessionEntity, @PathVariable int itemId) {
        final IdentificationType identifiedItem = unidentifiedItemIdCalculator.isIdentified(itemId);

        if (identifiedItem == IdentificationType.UNIDENTIFIED) {
            itemId = unidentifiedItemIdCalculator.getRealItemId(sessionEntity, itemId);
        }

        final ItemDefinition itemDefinition = itemDefinitionCache.getDefinition(itemId);
        if (inventoryFacade.getInventory(user).hasItem(itemDefinition, identifiedItem) && itemDefinitionCache.getDefinition(itemId).isEquipment()) {
            if (equipmentFacade.getEquipment(user).equipItem(itemDefinitionCache.getDefinition(itemId), identifiedItem == IdentificationType.IDENTIFIED)) {
                return equipmentResponseBuilder.build(user, EquipmentResponseBuilder.SUCCESSFULL_REQUEST);
            }
        }

        return equipmentResponseBuilder.build(user, EquipmentResponseBuilder.UNSUCCESSFULL_REQUEST);
    }


    @RequestMapping(value = "/unequip/{slotId}", method = RequestMethod.GET)
    public CharacterRefreshResponse unequip(UserEntity user, @PathVariable String slotId) {
        //TODO has enough inventory slots
        equipmentFacade.getEquipment(user).unequipItem(EquipmentSlot.valueOf(slotId));

        return equipmentResponseBuilder.build(user, EquipmentResponseBuilder.SUCCESSFULL_REQUEST);
    }
}
