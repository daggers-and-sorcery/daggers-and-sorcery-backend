package com.morethanheroic.swords.shop.view.controller;

import com.morethanheroic.swords.response.domain.Response;
import com.morethanheroic.swords.shop.service.ShopFacade;
import com.morethanheroic.swords.shop.view.response.ShopItemListResponseBuilder;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShopListMainStockController {

    @Autowired
    private ShopFacade shopFacade;

    @Autowired
    private ShopItemListResponseBuilder shopItemListResponseBuilder;

    @RequestMapping(value = "/shop/main", method = RequestMethod.GET)
    public Response listStock(UserEntity user) {
        return shopItemListResponseBuilder.build(user, shopFacade.getShopEntity(user.getMap().getMainShop()));
    }
}
