package com.morethanheroic.swords.shop.view.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.exception.NotFoundException;
import com.morethanheroic.session.domain.SessionEntity;
import com.morethanheroic.swords.inventory.domain.IdentificationType;
import com.morethanheroic.swords.inventory.service.UnidentifiedItemIdCalculator;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.shop.service.ShopEntityFactory;
import com.morethanheroic.swords.shop.service.ShopService;
import com.morethanheroic.swords.shop.service.definition.cache.ShopDefinitionCache;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ShopSellItemController {

    private final UnidentifiedItemIdCalculator unidentifiedItemIdCalculator;
    private final ItemDefinitionCache itemDefinitionCache;
    private final ResponseFactory responseFactory;
    private final ShopDefinitionCache shopDefinitionCache;
    private final ShopEntityFactory shopEntityFactory;
    private final ShopService shopService;

    //TODO: Use post and real validation with spring validation
    @GetMapping(value = "/shop/{shopId}/sell/{itemId}")
    public Response sellItem(UserEntity user, SessionEntity sessionEntity, @PathVariable int shopId, @PathVariable int itemId) {
        if (!shopDefinitionCache.isDefinitionExists(shopId)) {
            throw new NotFoundException();
        }

        final IdentificationType identificationType = unidentifiedItemIdCalculator.isIdentified(itemId);

        if (identificationType == IdentificationType.UNIDENTIFIED) {
            itemId = unidentifiedItemIdCalculator.getRealItemId(sessionEntity, itemId);
        }

        if (!itemDefinitionCache.isDefinitionExists(itemId)) {
            throw new NotFoundException();
        }

        shopService.userSellItem(user, shopEntityFactory.getEntity(shopId), itemDefinitionCache.getDefinition(itemId), identificationType);

        return responseFactory.newSuccessfulResponse(user);
    }
}
