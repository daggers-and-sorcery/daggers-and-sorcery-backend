package com.morethanheroic.swords.shop.view.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.exception.ConflictException;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.shop.domain.ShopDefinition;
import com.morethanheroic.swords.shop.service.ShopEntityFactory;
import com.morethanheroic.swords.shop.service.ShopService;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ShopBuyItemController {

    private final ResponseFactory responseFactory;
    private final ShopEntityFactory shopEntityFactory;
    private final ShopService shopService;

    //TODO: Use post and real validation with spring validation
    @GetMapping("/shop/{shopId}/buy/{itemId}")
    public Response buyItem(final UserEntity userEntity, final @PathVariable("shopId") ShopDefinition shopDefinition, final  @PathVariable("itemId") ItemDefinition itemDefinition) {
        if(!shopDefinition.getAvailableFeatures().isBuying()) {
            throw new ConflictException();
        }

        shopService.userBuyItem(userEntity, shopEntityFactory.getEntity(shopDefinition), itemDefinition);

        return responseFactory.newSuccessfulResponse(userEntity);
    }
}
