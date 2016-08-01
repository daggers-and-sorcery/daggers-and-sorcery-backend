package com.morethanheroic.swords.shop.view.controller;

import com.morethanheroic.session.domain.SessionEntity;
import com.morethanheroic.swords.response.domain.CharacterRefreshResponse;
import com.morethanheroic.swords.shop.service.ShopEntityFactory;
import com.morethanheroic.swords.shop.service.response.ShopItemListResponseBuilder;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShopListMainStockController {

    @Autowired
    private ShopEntityFactory shopEntityFactory;

    @Autowired
    private ShopItemListResponseBuilder shopItemListResponseBuilder;

    @RequestMapping(value = "/shop/main", method = RequestMethod.GET)
    public CharacterRefreshResponse listStock(UserEntity user, SessionEntity sessionEntity) {
        //TODO: shop is hardcoded for now!
        return shopItemListResponseBuilder.build(user, sessionEntity, shopEntityFactory.getEntity(1));
    }
}
