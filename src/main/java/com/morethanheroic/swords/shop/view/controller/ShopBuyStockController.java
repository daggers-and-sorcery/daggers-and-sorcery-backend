package com.morethanheroic.swords.shop.view.controller;

import com.morethanheroic.swords.common.response.ConflictException;
import com.morethanheroic.swords.common.response.NotFoundException;
import com.morethanheroic.swords.inventory.service.UnidentifiedItemIdCalculator;
import com.morethanheroic.swords.item.domain.ItemDefinition;
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
public class ShopBuyStockController {

    @Autowired
    private ShopFacade shopFacade;

    @Autowired
    private UnidentifiedItemIdCalculator unidentifiedItemIdCalculator;

    @Autowired
    private ItemDefinitionCache itemDefinitionCache;

    @Autowired
    private ResponseFactory responseFactory;

    @RequestMapping(value = "/shop/{shopId}/buy/{itemId}/{itemAmount}", method = RequestMethod.GET)
    public Response buyStock(UserEntity user, HttpSession httpSession, @PathVariable int shopId, @PathVariable int itemId, @PathVariable int itemAmount) {
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

        if (!user.getInventory().hasItemAmount(itemId, itemAmount, isIdentifiedItem)) {
            throw new ConflictException();
        }

        ShopEntity shopEntity = shopFacade.getShopEntity(shopId);
        ItemDefinition itemDefinition = itemDefinitionCache.getItemDefinition(itemId);

        if (!shopEntity.hasItem(itemDefinition, itemAmount)) {
            throw new ConflictException();
        }

        if (shopEntity.getShopSellPrice(itemDefinition) >= user.getInventory().getMoneyAmount()) {
            user.getInventory().decreaseMoneyAmount(shopEntity.getShopSellPrice(itemDefinition));

            user.getInventory().addItem(itemDefinition, itemAmount);

            shopEntity.sellItem(itemDefinition, itemAmount);
        }

        return responseFactory.newSuccessfulResponse(user);
    }
}
