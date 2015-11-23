package com.morethanheroic.swords.shop.view.controller;

import com.morethanheroic.swords.common.response.NotFoundException;
import com.morethanheroic.swords.response.domain.Response;
import com.morethanheroic.swords.shop.service.ShopFacade;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShopSellStockController {

    @Autowired
    private ShopFacade shopFacade;

    @RequestMapping(value = "/shop/{shopId}/sell/{itemId}/{itemAmount}", method = RequestMethod.GET)
    public Response listStock(UserEntity user, @PathVariable int shopId, @PathVariable int itemId, @PathVariable int itemAmount) {
        if (!shopFacade.isShopExists(shopId)) {
            throw new NotFoundException();
        }
        
        return null;
    }
}
