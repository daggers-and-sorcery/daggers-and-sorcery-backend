package com.morethanheroic.swords.equipment.view.controller;

import com.morethanheroic.swords.common.response.Response;
import com.morethanheroic.swords.equipment.service.EquipmentManager;
import com.morethanheroic.swords.equipment.service.EquipmentResponseBuilder;
import com.morethanheroic.swords.inventory.service.InventoryManager;
import com.morethanheroic.swords.item.service.ItemDefinitionManager;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EquipmentController {

    private final EquipmentManager equipmentManager;
    private final InventoryManager inventoryManager;
    private final ItemDefinitionManager itemDefinitionManager;
    private final EquipmentResponseBuilder equipmentResponseBuilder;

    @Autowired
    public EquipmentController(InventoryManager inventoryManager, ItemDefinitionManager itemDefinitionManager, EquipmentManager equipmentManager, EquipmentResponseBuilder equipmentResponseBuilder) {
        this.inventoryManager = inventoryManager;
        this.itemDefinitionManager = itemDefinitionManager;
        this.equipmentManager = equipmentManager;
        this.equipmentResponseBuilder = equipmentResponseBuilder;
    }

    @RequestMapping(value = "/equip/{itemId}", method = RequestMethod.GET)
    public Response equip(UserEntity user, @PathVariable int itemId) {
        if (inventoryManager.getInventory(user).hasItem(itemId) && itemDefinitionManager.getItemDefinition(itemId).isEquipment()) {
            //TODO: check if user has any needed levles and attribute limits
            equipmentManager.getEquipment(user).equipItem(itemDefinitionManager.getItemDefinition(itemId));

            return equipmentResponseBuilder.build(EquipmentResponseBuilder.SUCCESSFULL_REQUEST);
        }

        return equipmentResponseBuilder.build(EquipmentResponseBuilder.UNSUCCESSFULL_REQUEST);
    }
}
