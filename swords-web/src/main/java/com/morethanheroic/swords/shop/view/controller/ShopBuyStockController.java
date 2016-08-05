package com.morethanheroic.swords.shop.view.controller;

import com.morethanheroic.response.exception.ConflictException;
import com.morethanheroic.response.exception.NotFoundException;
import com.morethanheroic.session.domain.SessionEntity;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryFacade;
import com.morethanheroic.swords.inventory.service.UnidentifiedItemIdCalculator;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.money.domain.MoneyType;
import com.morethanheroic.swords.response.domain.CharacterRefreshResponse;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.shop.domain.ShopEntity;
import com.morethanheroic.swords.shop.service.ItemPriceCalculator;
import com.morethanheroic.swords.shop.service.ShopEntityFactory;
import com.morethanheroic.swords.shop.service.cache.ShopDefinitionCache;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShopBuyStockController {

    @Autowired
    private UnidentifiedItemIdCalculator unidentifiedItemIdCalculator;

    @Autowired
    private ItemDefinitionCache itemDefinitionCache;

    @Autowired
    private ResponseFactory responseFactory;

    @Autowired
    private ShopDefinitionCache shopDefinitionCache;

    @Autowired
    private InventoryFacade inventoryFacade;

    @Autowired
    private ItemPriceCalculator itemPriceCalculator;

    @Autowired
    private ShopEntityFactory shopEntityFactory;

    @RequestMapping(value = "/shop/{shopId}/buy/{itemId}", method = RequestMethod.GET)
    public CharacterRefreshResponse buyStock(UserEntity user, SessionEntity sessionEntity, @PathVariable int shopId, @PathVariable int itemId) {
        if (!shopDefinitionCache.isDefinitionExists(shopId)) {
            throw new NotFoundException();
        }

        boolean isIdentifiedItem = unidentifiedItemIdCalculator.isIdentifiedItem(itemId);

        if (!isIdentifiedItem) {
            itemId = unidentifiedItemIdCalculator.getRealItemId(sessionEntity, itemId);
        }

        if (!itemDefinitionCache.isDefinitionExists(itemId)) {
            throw new NotFoundException();
        }

        ShopEntity shopEntity = shopEntityFactory.getEntity(shopId);
        ItemDefinition itemDefinition = itemDefinitionCache.getDefinition(itemId);

        if (!shopEntity.hasItem(itemDefinition, 1)) {
            throw new ConflictException();
        }

        //TODO: Check that the player is on the shop's position except if its the main shop.
        //TODO: Use main shop rates if the player using the main shop.

        final InventoryEntity inventoryEntity = inventoryFacade.getInventory(user);

        //ATM we only use money as money, no support for special trades
        if (itemPriceCalculator.getSellPrice(itemDefinition) <= inventoryEntity.getMoneyAmount(MoneyType.MONEY)) {
            inventoryEntity.decreaseMoneyAmount(MoneyType.MONEY, itemPriceCalculator.getSellPrice(itemDefinition));

            inventoryEntity.addItem(itemDefinition, 1);

            shopEntity.sellItem(itemDefinition, 1);
        }

        return responseFactory.newSuccessfulResponse(user);
    }
}
