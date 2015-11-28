package com.morethanheroic.swords.shop.view.controller;

import com.morethanheroic.swords.common.response.ConflictException;
import com.morethanheroic.swords.common.response.NotFoundException;
import com.morethanheroic.swords.inventory.service.UnidentifiedItemIdCalculator;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.domain.ItemType;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.response.domain.Response;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.shop.domain.ShopEntity;
import com.morethanheroic.swords.shop.service.ShopFacade;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class ShopSellStockController {

    @Autowired
    private ShopFacade shopFacade;

    @Autowired
    private UnidentifiedItemIdCalculator unidentifiedItemIdCalculator;

    @Autowired
    private ItemDefinitionCache itemDefinitionCache;

    @Autowired
    private ResponseFactory responseFactory;

    @RequestMapping(value = "/shop/{shopId}/sell/{itemId}", method = RequestMethod.GET)
    public Response sellStock(UserEntity user, HttpSession httpSession, @PathVariable int shopId, @PathVariable int itemId) {
        if (!shopFacade.isShopExists(shopId)) {
            throw new NotFoundException();
        }

        boolean isIdentifiedItem = unidentifiedItemIdCalculator.isIdentifiedItem(itemId);

        if (!isIdentifiedItem) {
            itemId = unidentifiedItemIdCalculator.getRealItemId(httpSession, itemId);
        }

        if (!itemDefinitionCache.isItemExists(itemId)) {
            throw new NotFoundException();
        }

        if (!user.getInventory().hasItemAmount(itemId, 1, isIdentifiedItem)) {
            throw new ConflictException();
        }

        //TODO: Check that the player is on the shop's position except if its the main shop
        //TODO: Use main shop rates if the player using the main shop

        ItemDefinition itemDefinition = itemDefinitionCache.getItemDefinition(itemId);

        if(itemDefinition.getType() == ItemType.COIN) {
            throw new ConflictException();
        }

        ShopEntity shopEntity = shopFacade.getShopEntity(shopId);

        user.getInventory().increaseMoneyAmount(shopEntity.getShopBuyPrice(itemDefinition));
        user.getInventory().removeItem(itemId, 1, isIdentifiedItem);

        shopEntity.buyItem(itemDefinition, 1);

        return responseFactory.newSuccessfulResponse(user);
    }
}
