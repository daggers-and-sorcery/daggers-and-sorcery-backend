package com.morethanheroic.swords.equipment.view.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.session.domain.SessionEntity;
import com.morethanheroic.swords.equipment.service.EquippingService;
import com.morethanheroic.swords.inventory.domain.IdentificationType;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.inventory.service.UnidentifiedItemIdCalculator;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.service.definition.cache.ItemDefinitionCache;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EquipmentEquipController {

    private final InventoryEntityFactory inventoryEntityFactory;
    private final ItemDefinitionCache itemDefinitionCache;
    private final EquippingService equippingService;
    private final ResponseFactory responseFactory;

    private final UnidentifiedItemIdCalculator unidentifiedItemIdCalculator;

    @GetMapping("/equip/{itemId}")
    public Response equip(final UserEntity userEntity, final SessionEntity sessionEntity, @PathVariable int itemId) {
        final IdentificationType identifiedItem = unidentifiedItemIdCalculator.isIdentified(itemId);

        if (identifiedItem == IdentificationType.UNIDENTIFIED) {
            itemId = unidentifiedItemIdCalculator.getRealItemId(sessionEntity, itemId);
        }

        final ItemDefinition itemToEquip = itemDefinitionCache.getDefinition(itemId);
        if (inventoryEntityFactory.getEntity(userEntity).hasItem(itemToEquip, identifiedItem) && itemToEquip.isEquipment()) {
            if (equippingService.equipItem(userEntity, itemToEquip, identifiedItem)) {
                return responseFactory.successfulResponse(userEntity);
            }
        }

        return responseFactory.failedResponse(userEntity);
    }
}
