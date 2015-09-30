package com.morethanheroic.swords.item.view.controller;

import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryManager;
import com.morethanheroic.swords.item.service.UseItemService;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UseItemController {

    private final InventoryManager inventoryManager;
    private final UseItemService useItemService;

    @Autowired
    public UseItemController(InventoryManager inventoryManager, UseItemService useItemService) {
        this.inventoryManager = inventoryManager;
        this.useItemService = useItemService;
    }

    @Transactional
    @RequestMapping(value = "/item/use/{itemId}", method = RequestMethod.GET)
    public void useItem(UserEntity userEntity, @PathVariable int itemId) {
        InventoryEntity inventoryEntity = inventoryManager.getInventory(userEntity);

        if (inventoryEntity.hasItem(itemId)) {
            useItemService.useItem(userEntity, itemId);
        }
    }
}
