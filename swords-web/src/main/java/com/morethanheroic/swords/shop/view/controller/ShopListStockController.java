package com.morethanheroic.swords.shop.view.controller;

import com.morethanheroic.response.exception.NotFoundException;
import com.morethanheroic.session.domain.SessionEntity;
import com.morethanheroic.swords.response.domain.CharacterRefreshResponse;
import com.morethanheroic.swords.shop.service.ShopEntityFactory;
import com.morethanheroic.swords.shop.service.cache.ShopDefinitionCache;
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
    private ShopDefinitionCache shopDefinitionCache;

    @Autowired
    private ShopEntityFactory shopEntityFactory;

    @Autowired
    private ShopItemListResponseBuilder shopItemListResponseBuilder;

    @RequestMapping(value = "/shop/{shopId}", method = RequestMethod.GET)
    public CharacterRefreshResponse listStock(UserEntity user, @PathVariable int shopId, SessionEntity sessionEntity) {
        if (!shopDefinitionCache.isDefinitionExists(shopId)) {
            throw new NotFoundException();
        }

        //TODO: Check that the user is in the same city as the shop

        return shopItemListResponseBuilder.build(user, sessionEntity, shopEntityFactory.getEntity(shopId));
    }
}
