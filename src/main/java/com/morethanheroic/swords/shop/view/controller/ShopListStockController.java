package com.morethanheroic.swords.shop.view.controller;

import com.morethanheroic.response.exception.NotFoundException;
import com.morethanheroic.swords.response.domain.CharacterRefreshResponse;
import com.morethanheroic.swords.shop.service.ShopFacade;
import com.morethanheroic.swords.shop.service.response.ShopItemListResponseBuilder;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class ShopListStockController {

    @Autowired
    private ShopFacade shopFacade;

    @Autowired
    private ShopItemListResponseBuilder shopItemListResponseBuilder;

    @RequestMapping(value = "/shop/{shopId}", method = RequestMethod.GET)
    public CharacterRefreshResponse listStock(UserEntity user, @PathVariable int shopId, HttpSession httpSession) {
        if (!shopFacade.isShopExists(shopId)) {
            throw new NotFoundException();
        }

        //TODO: Check that the user is on the same tile as the shop

        return shopItemListResponseBuilder.build(user, httpSession, shopFacade.getShopEntity(shopId));
    }
}
