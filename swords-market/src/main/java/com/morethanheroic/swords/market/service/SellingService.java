package com.morethanheroic.swords.market.service;

import org.springframework.stereotype.Service;

import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.market.domain.SellingResult;
import com.morethanheroic.swords.market.repository.repository.MarketMapper;
import com.morethanheroic.swords.user.domain.UserEntity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SellingService {

    private final MarketMapper marketMapper;
    private final ItemDefinitionCache itemDefinitionCache;
    private final InventoryEntityFactory inventoryEntityFactory;

    public SellingResult sellToMarket(final UserEntity userEntity, final ItemDefinition item, final int price, final int amount) {
        if (!itemDefinitionCache.isDefinitionExists(item.getId())) {
            return SellingResult.ITEM_DOES_NOT_EXIST;
        }

        final InventoryEntity inventoryEntity = inventoryEntityFactory.getEntity(userEntity.getId());

        if (!inventoryEntity.hasItemAmount(item, amount)) {
            return SellingResult.INSUFFICIENT_RESOURCES;
        }

        log.info("The user is trying to sell the item: " + item + " with price: " + price + " to the market!");

        marketMapper.insertMarketOffer(userEntity.getId(), item.getId(), price, amount);
        inventoryEntity.removeItem(item, amount);

        return SellingResult.SUCCESSFUL_TRANSACTION;
    }
}
