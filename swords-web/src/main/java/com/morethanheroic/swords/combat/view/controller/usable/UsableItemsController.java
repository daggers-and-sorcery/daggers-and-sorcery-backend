package com.morethanheroic.swords.combat.view.controller.usable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.combat.view.response.service.usable.UsableItemsResponseBuilder;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.user.domain.UserEntity;

@RestController
public class UsableItemsController {

    @Autowired
    private UsableItemsResponseBuilder usableItemsResponseBuilder;

    @Autowired
    private InventoryEntityFactory inventoryEntityFactory;

    @RequestMapping(value = "/combat/usable/item", method = RequestMethod.GET)
    public Response usableItems(UserEntity userEntity) {
        return usableItemsResponseBuilder.build(userEntity, inventoryEntityFactory.getEntity(userEntity.getId()).getItems());
    }
}
