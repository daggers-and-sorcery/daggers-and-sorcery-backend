package com.morethanheroic.swords.shop.view.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.exception.ConflictException;
import com.morethanheroic.swords.item.view.request.advice.domain.ItemRequestEntity;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.shop.domain.ShopDefinition;
import com.morethanheroic.swords.shop.service.ShopEntityFactory;
import com.morethanheroic.swords.shop.service.ShopService;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ShopSellItemController {

    private final ResponseFactory responseFactory;
    private final ShopEntityFactory shopEntityFactory;
    private final ShopService shopService;

    //TODO: Use post instead of get
    @GetMapping("/shop/{shopId}/sell/{itemId}")
    public Response sellItem(final UserEntity userEntity, final @PathVariable("shopId") ShopDefinition shopDefinition, final  @PathVariable("itemId") ItemRequestEntity itemRequestEntity) {
        if(!shopDefinition.getAvailableFeatures().isSelling()) {
            throw new ConflictException();
        }

        shopService.userSellItem(userEntity, shopEntityFactory.getEntity(shopDefinition), itemRequestEntity.getItemDefinition(), itemRequestEntity.getIdentificationType());

        return responseFactory.successfulResponse(userEntity);
    }
}
