package com.morethanheroic.swords.shop.view.controller;

import com.morethanheroic.swords.common.response.NotFoundException;
import com.morethanheroic.swords.response.domain.Response;
import com.morethanheroic.swords.shop.service.ShopFacade;
import com.morethanheroic.swords.shop.service.response.ShopItemListResponseBuilder;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShopListStockController {

    @Autowired
    private ShopFacade shopFacade;

    @Autowired
    private ShopItemListResponseBuilder shopItemListResponseBuilder;

    @RequestMapping(value = "/shop/{shopId}", method = RequestMethod.GET)
    public Response listStock(UserEntity user, @PathVariable int shopId) {
        if (!shopFacade.isShopExists(shopId)) {
            throw new NotFoundException();
        }

        //TODO: Check that the user is on the same tile as the shop

        return shopItemListResponseBuilder.build(user, shopFacade.getShopEntity(shopId));
    }
}
