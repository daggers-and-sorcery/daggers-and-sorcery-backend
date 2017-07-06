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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ShopBuyItemController {

    private final UnidentifiedItemIdCalculator unidentifiedItemIdCalculator;
    private final ItemDefinitionCache itemDefinitionCache;
    private final ResponseFactory responseFactory;
    private final ShopDefinitionCache shopDefinitionCache;
    private final ShopEntityFactory shopEntityFactory;
    private final ShopService shopService;

    //TODO: Use post and real validation with spring validation
    @GetMapping(value = "/shop/{shopId}/buy/{itemId}")
    public Response buyItem(UserEntity userEntity, SessionEntity sessionEntity, @PathVariable int shopId, @PathVariable int itemId) {
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

        shopService.userBuyItem(userEntity, shopEntityFactory.getEntity(shopId), itemDefinitionCache.getDefinition(itemId));

        return responseFactory.newSuccessfulResponse(userEntity);
    }
}
